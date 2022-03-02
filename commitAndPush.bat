@echo off
SET PATH=%PATH%;D:\PortableGit\bin
git add ./src/*
git commit -m "commitAndPush"
git push origin main
exit