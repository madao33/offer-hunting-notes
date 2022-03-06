# chap3-Deep-Learning-with-Pytorch

## Gradients

`torch`自动实现了梯度计算，需要添加`requires_grad=True`属性，例如

```python
v1 = torch.tensor([1.0, 1.0], requires_grad = True)
```

## tensorboard

使用`tensorboard`的命令行`tensorboard --logdir runs`，然后点击链接：

## GAN on Atari images

>  这个Demo遇到了一些bug，提示总是环境无法加载，发现是环境太老了，重新安装旧版本的`gym`和`atari`
>
>  ```shell
>  pip install gym==0.19.0
>  pip install atari_py==0.2.6
>  ```







