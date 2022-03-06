# git笔记

## 遇到的一些`bug`

## 合并分支

> 在20年6月前，github的仓库默认为`master`，但是受到美国大规模的**Black Lives Matter**运动的影响，github用`main`替换了`master`

例如开发过程中有`main`分支，这个是主要的分支，开发人员直接开发的中间分支是`dev`，想要将`dev`分支的代码合并到`main`分支，可以进行如下操作

* 首先切换到`master`分支上

  ```shell
  git checkout master
  ```

* 如果多人开发，需要下载最新的`master`代码，先从远程`pull`下来

  ```shell
  git pull origin master
  ```

* 然后将`dev`分支的代码合并到`master`上

  ```shell
  git merge dev
  ```

## 删除分支

* 查看所有分支

  ```shell
  git branch -a
  ```

* 查看当前分支

  ```shell
  git branch
  ```

* 删除本地的问题分支

  ```shell
  git branch -d bug-branch
  ```

* 删除远程的问题分支

  ```shell
  git push origin --delete bug-branch
  ```

> 因为在`ubuntu`下载远程仓库，然后修改了代码，准备上传，结果发现分支不对劲，默认下载的代码给我创建的分支居然是`master`，我吐了，就进行两个步骤的处理，重新合并分支，并删除分支`master`，还是不能用`master`   **BLM WARNING!**

