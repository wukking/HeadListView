# HeadListView
仿QQ个人界面下滑放大上面的图片背景。


1.创建一个子View继承一个ListView，

2.创建一个set方法将图片导入到mlv中操作；

3.用全局监听获取imageView的高度和图片的高度；

imageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
  @Override
    public void onGlobalLayout() {
      imageView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
      orignalHeight = imageView.getHeight();        //imageVIew高度
      int drawableHeight = imageView.getDrawable().getIntrinsicHeight();//图片的高度
      maxHeight = orignalHeight>drawableHeight?orignalHeight*2:drawableHeight;
  }
  
4.在重写的overScrollBy（）中操作放大图片；

5.把图片传进；

View headerView = View.inflate(this,R.layout.layout_head,null);
ImageView imageView = (ImageView) headerView.findViewById(R.id.imageView);

6.消除ListView滑到顶部或者底部的阴影（plistView.setOverScrollMode(ListView.OVER_SCROLL_NEVER);）；
