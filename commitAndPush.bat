@echo off
SET PATH=%PATH%;D:\PortableGit\bin
git add .
git commit -a -m "commitAndPush"
git push origin main
exit