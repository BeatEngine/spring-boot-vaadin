/**
 * Title: UserRightsPathMatcher.
 * Description: Die Rechte werden hier den entsprechenden URL-Pfaden zugeordnet.
 * Copyright EDAG Engineering GmbH 2022
 *
 * @author dg79696
 */
package org.fbs.planner.security;

import org.fbs.planner.entity.Permission;
import org.fbs.planner.entity.RoleUser;
import org.fbs.planner.entity.idclass.RoleUserId;
import org.fbs.planner.repository.PermissionRepository;
import org.fbs.planner.repository.PermissionRoleRepository;
import org.fbs.planner.repository.RoleUserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public class UserPermissionsPathMatcher
{

	private final List<Permission> userPermissions;

	/**
	 * Konstruktor
	 * Alle Rechte des Users werden aus Datenbank abgefragt.
	 *
	 * @param userId          Id des Users
	 * @param userRoles       Repository für Benutzerrollen
	 * @param permissions     Repository für Rechte
	 * @param permissionRoles Repository für Rechte der Rollen
	 */
	public UserPermissionsPathMatcher(final long userId, final RoleUserRepository userRoles,
												 final PermissionRepository permissions,
												 final PermissionRoleRepository permissionRoles)
	{
		final Set<Long>      permissionIds = new HashSet<Long>();
		final List<RoleUser> roles         = userRoles.findByUserId(userId);
		//Alle RechteIds für alle Rollen, die der User hat.
		for (final RoleUser roleUser : roles)
		{
			final long[] permissionIdByRoleId = permissionRoles.findPermissionIdByRoleId(roleUser.getRoleId());
			for (final long pid : permissionIdByRoleId)
			{
				permissionIds.add(Long.valueOf(pid));
			}
		}
		//Rechte per Ids auslesen.
		userPermissions = permissions.findByPermissionIdIn(permissionIds);
	}

	/**
	 * Hat ein Benutzer eine bestimmte Berechtigung?
	 *
	 * @param name Name der Berechtigung.
	 * @return wahr, wenn diese besitzt wird.
	 */
	private boolean hasPermission(final String name)
	{
		final ListIterator<Permission> iterator = userPermissions.listIterator();
		while (iterator.hasNext())
		{
			final Permission next = iterator.next();
			if (next.getName().equals(name))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Prüfen, ob Nutzer für diese URL berechtigt ist.
	 *
	 * @param relativeUrlPath URL für Prüfung.
	 * @return Wahr, wenn Rechte vorhanden.
	 */
	public boolean userHasPermissionFor(final String relativeUrlPath)
	{
		if ("/leftmenustate".equals(relativeUrlPath) ||
			 "/".equals(relativeUrlPath) ||
			 hasPermission("globaladmin") && false)
		{
			//Darf alles.
			return true;
		}

		//Blacklist - Fehlende Berechtigungen blockieren.
		if ((relativeUrlPath.startsWith("/instances") ||
			  relativeUrlPath.startsWith("/instance") ||
			  relativeUrlPath.startsWith("/tester") ||
			  relativeUrlPath.startsWith("/passitem") ||
			  relativeUrlPath.startsWith("/failitem") ||
			  relativeUrlPath.startsWith("/undoitem") ||
			  relativeUrlPath.startsWith("/refreshallcomments") ||
			  relativeUrlPath.startsWith("/loadcomment") ||
			  relativeUrlPath.startsWith("/removecomment") ||
			  relativeUrlPath.startsWith("/skipitem")) && !hasPermission("testinstances_run"))
		{
			//Darf nicht !
			return false;
		}

		return true;
	}

	/**
	 * Umwandeln eines SpringPfad in ein dazu passendes Pattern
	 * IN(/admin/testcases/{id}/setlevel) --> OUT(\/admin\/testcases\//d+\/setlevel.*)
	 *
	 * @param pathDeclaration Pattern für PAth-Match z.B. "\/admin\/testcases\//d+\/setlevel.*"
	 * @return
	 */ //todo test ist notwendig!
	public static String createPatternOfSpringPath(final String pathDeclaration)
	{
		final String prepared = pathDeclaration.replace("/", "\\/");
		if (prepared.contains("{") && prepared.contains("}"))
		{
			// /abc/{id]/cde --> /abc/, /cde/ --> \/abc\//d+\/cde
			String result = prepared.replaceAll("\\{(\\d+|\\w+)\\}", "(\\d+|\\w+)");
			//todo bei LOGGING INFO
			result = result.replace("d+|w+", "\\d+|\\w+");
			//System.out.println("Generated Pattern for Error: " + pathDeclaration + " --> " + result);
			return result;
		}
		//todo bei LOGGING INFO
		//System.out.println("Generated Pattern for Error: " + pathDeclaration + " --> " + prepared);
		return prepared + ".*";
	}
}
