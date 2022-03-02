@echo off
SET PATH=%PATH%;D:\PortableGit\bin
git add .
git pull https://github.com/BeatEngine/spring-boot-vaadin refs/heads/main:upstream/main
git commit -a -m "commitAndPush"
git push origin main
exit