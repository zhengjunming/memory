## Git语法
### 一、创建版本库
1. 初始化一个Git仓库： git init
2. 添加文件到G仓库
> * 使用命令git add <file>，可反复多次使用，添加多个文件
> * 使用命令git commit，完成
### 二、时光机穿梭
* 使用git status命令。随时掌握工作区的状态
* 如果git status告诉你有文件被修改过，用git diff可以查看修改内容。
### 三、版本回退
* HEAD指向的版本就是当前版本，因此，Git允许我们在版本的历史之间穿梭，使用命令“git reset --hard commit_id。

* 穿梭前，用git log可以查看提交历史，以便确定要回退到哪个版本。

* 要重返未来，用git reflog查看命令历史，以便确定要回到未来的哪个版本。
### 四、撤销修改
* 场景1：当你改乱了工作区某个文件的内容，想直接丢弃工作区的修改时，用命令git checkout -- file。

* 场景2：当你不但改乱了工作区某个文件的内容，还添加到了暂存区时，想丢弃修改，分两步，第一步用命令git reset HEAD file，就回到了场景1，第二步按场景1操作。

* 场景3：已经提交了不合适的修改到版本库时，想要撤销本次提交，参考版本回退一节，不过前提是没有推送到远程库。
### 五、关联远程库
* 要关联一个远程库，使用命令git remote add origin git@server-name:path/repo-name.git；

* 关联后，使用命令git push -u origin master第一次推送master分支的所有内容；

* 此后，每次本地提交后，只要有必要，就可以使用命令git push origin master推送最新修改