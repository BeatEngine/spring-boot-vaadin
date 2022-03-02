/**
 * Title: ProcessUtilService.
 * Description:
 * Copyright EDAG Engineering GmbH 2020
 *
 * @author dg79696
 */
package org.fbs.planner.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UtilService
{
	/**
	 * Umwandelung url http request parameter in Map
	 * @param request "?a=1&b=2&c=345
	 * @Return Map zuordnung Variable zu Wert
	 */
	public static Map<String, String> processRequestValuesByStringSTD(final String request)
	{
		Map<String, String> parsed = new HashMap<String, String>();

		String[] vars = request.split("&|\\?");

		for (int i = 0; i < vars.length; i++)
		{
			String[] pair = vars[i].split("=");
			if (pair.length == 2)
			{
				parsed.put(pair[0], pair[1].trim());
			}
			else if (pair.length == 1)
			{
				parsed.put(pair[0], "");
			}
		}
		return parsed;
	}

	/**
	 * Umwandelung url http request parameter in Map und UTF8 dekodieren
	 * @param request "?a=1&b=2&c=3%2045 --> a=1, b=2, c="3 45"
	 * @Return Map zuordnung Variable zu Wert (dekodiert)
	 */
	public static Map<String, String> processRequestValuesByStringSTD(final String request,final boolean decodeUTF8)
	{
		Map<String, String> parsed = new HashMap<String, String>();

		String[] vars = request.split("&|\\?");

		for (int i = 0; i < vars.length; i++)
		{
			String[] pair = vars[i].split("=");
			if (pair.length == 2)
			{
				if(decodeUTF8)
				{
					try
					{
						pair[0] = URLDecoder.decode(pair[0], "UTF-8");
					}
					catch (UnsupportedEncodingException e)
					{
						e.printStackTrace();
					}
					try
					{
						pair[1] = URLDecoder.decode(pair[1].trim(), "UTF-8");
					}
					catch (UnsupportedEncodingException e)
					{
						e.printStackTrace();
					}
				}
				parsed.put(pair[0], pair[1].trim());
			}
			else if (pair.length == 1)
			{
				if(decodeUTF8)
				{
					try
					{
						pair[0] = URLDecoder.decode(pair[0], "UTF-8");
					}
					catch (UnsupportedEncodingException e)
					{
						e.printStackTrace();
					}
				}
				parsed.put(pair[0], "");
			}
		}
		return parsed;
	}

	/**
	 * @param request "?a=1&b=2&c=345
	 */
	public static String[] processRequestValuesByString(String request)
	{
		String[] vars;
		if (request.contains("&"))
		{
			vars = request.split("&");
		}
		else
		{
			vars = new String[1];
			vars[0] = request;
		}

		boolean vali = true;
		for (int i = 0; i < vars.length; i++)
		{
			if (!vars[i].contains("="))
			{
				vali = false;
				break;
			}
			if (vars[i].endsWith("="))
			{
				vars[i] += ' ';
			}
		}
		if (!vali)
		{
			return new String[0];
		}
		return vars;
	}

	/**
	 * Den ersten Schlüssel einer Map per zugeordneten Wert bekommen.
	 * @Return null oder den gefundenen Schlüssel.
	 * */
	public static <T, V> T getFirstKeyOfValue(final Map<T, V> map, final V value)
	{
		final Set<Map.Entry<T, V>> entries = map.entrySet();
		final Iterator iterator = entries.iterator();
		while(iterator.hasNext())
		{
			final Map.Entry<T, V> next = (Map.Entry<T, V>)iterator.next();
			if(next.getValue().equals(value))
			{
				return next.getKey();
			}
		}
		return null;
	}

	/**
	 * Den ersten Schlüssel einer Map per zugeordneten enthalten Wert bekommen.
	 * @Return null oder den gefundenen Schlüssel.
	 * */
	public static <T> T getFirstKeyOfValueContained(final Map<T, String> map, final String containedValue)
	{
		final Set<Map.Entry<T, String>> entries = map.entrySet();
		final Iterator iterator = entries.iterator();
		while(iterator.hasNext())
		{
			final Map.Entry<T, String> next = (Map.Entry<T, String>)iterator.next();
			if(next.getValue().toString().contains(containedValue))
			{
				return next.getKey();
			}
		}
		return null;
	}

	/**
	 * Alle Schlüssel einer Map per zugeordneten Wert bekommen.
	 * @Return List der gefundenen Schlüssel.
	 * */
	public static <T, V> List<T> getKeysOfValue(final Map<T, V> map, final V value)
	{
		final Set<Map.Entry<T, V>> entries  = map.entrySet();
		final Iterator             iterator = entries.iterator();
		List<T>                    results  = new ArrayList<T>();
		while(iterator.hasNext())
		{
			final Map.Entry<T, V> next = (Map.Entry<T, V>)iterator.next();
			if(next.getValue().equals(value))
			{
				results.add(next.getKey());
			}
		}
		return results;
	}


	/**
	 * Gibt sortierte Werteliste einer Map zurück
	 * @Param map Quell-Map für Werte
	 * @Param order Comparator für Sortierung
	 * @Return Liste der nach "order" sortierten Werte der Map.
	 * */
	public static <T, V> List<V> valuesSorted(final Map<T, V> map, final Comparator<V> order)
	{
		List<V> l = new ArrayList<V>(map.values());
		l.sort(order);
		return l;
	}

	/** Der String wird auf Umwandlungskompatiblität für int, long geprüft.
	 * @Param jira_field_id zu testender String
	 * @Return true - kann in int, long umgewandelt werden.
	 * */
	public static boolean stringIsInteger(final String jiraFieldId)
	{
		if(jiraFieldId == null || jiraFieldId.isBlank())
		{
			return false;
		}
		//Integer-String enthält nur numerische Stellen
		for(int i = 0; i < jiraFieldId.length(); i++)
		{
			if(!Character.isDigit(jiraFieldId.charAt(i)))
			{
				return false;
			}
		}
		return true;
	}
}
