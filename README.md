# android 进阶 UI #
读源码一定要有线索
要带着自己的疑问来读源码

一、从setContentView开始，了解view的加载过程
	
	1、疑问：
	setContentView到底做了些什么，为什么调用后就可以显示出我们想要的布局页面？
	
	PhoneWindow倒是什么东西？Window和它是什么关系？
	
	DecorView是干什么用的？和我们的布局又有什么样的关系
	
	requestFeature为什么要在setContentView之前调用？
	
	2、LayoutInflater 到底怎么把xml添加到decorview？
	
		include 为什么不能xml资源布局的根节点？
		
		merge 为什么作为xml资源布局的根节点？
	
   小结：每一个Activity都有一个关联的Window对象，用来描述应用程序窗口。
		 每一个窗口内部又包含了一个DecorView对象，Decorview对象用来描述窗口的视图--xml布局
	
	上述是创建DecorView的过程
	
	3、DecorView如何添加到Window
	看图片，最终调用了ViewRootImpl.setView
	在setview方法里调用了view.assignParent(this);,将Decorview的mParent设置成ViewRootImpl
	这也就是为什么View再用requestLayout方法的时候最终会走到ViewRootImpl的requestLayout
	
     找到UI绘制流程的起始点
	 ViewRootImpl#performTraversals()
	 
	 ///测量
	 performMeasure()
	 // 摆放布局
	 performLayout()
	 // 绘制
	 performDraw()
	 
二、UI绘制流程
	1、Measure
		MeasureSpec：在Measure流程中，系统将View的LayoutParams根据父容器所施加的规则转换成对应的MeasureSpec，
		在onMeasure中根据这个MeasureSpec来确定view的测量宽高
		
		1）、测量模式
		EXACTLY ：父容器已经测量出所需要的精确大小，这也是childview的最终大小
				------match_parent，精确值
				
		ATMOST : child view最终的大小不能超过父容器的给的
				------wrap_content 
				
		UNSPECIFIED: 不确定，源码内部使用
				-------一般在ScrollView，ListView 
				
		2）、测量大小：根据测量模式来确定测量大小
	
		3）源码里面的位运算
		&：取出对应Mask类型的属性值
		|：添加对应的属性值
		& =~与非  或者（^异或）：去掉Mask类型的属性值
		
	2、View的测量
		onMeasure方法里面调用setMeasuredDimension（）确定当前View的大小
	
	3、ViewGroup的测量
		1、遍历测量Child，可以通过下面三个方法来遍历测量Child
			measureChildWithMargins
			measureChild
			measureChildren
		
		2、setMeasuredDimension 确定当前ViewGroup的大小
		
	4、假如去自定义View，ViewGroup，要如何做好Measure？
		1、View
			
			套路：最终调用setMeasuredDimession方法来保存自己的测量宽高
			final int specMode = MeasureSpec.getMode(measureSpec);
			final int specSize =  MeasureSpec.getSize(measureSpec);
			switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                /* Parent says we can be as big as we want. Just don't be larger
                   than max size imposed on ourselves.
                */
                result = Math.min(desiredSize, maxSize);
                break;
            case MeasureSpec.AT_MOST:
                // Parent says we can be as big as we want, up to specSize.
                // Don't be larger than specSize, and don't be larger than
                // the max size imposed on ourselves.
                result = Math.min(Math.min(desiredSize, specSize), maxSize);
                break;
            case MeasureSpec.EXACTLY:
                // No choice. Do what we are told.
                result = specSize;
                break;
        }
        return result;
			
		2、ViewGroup
		套路：
		1、测量子view的规格大小
			measureChildWithMargins
			measureChild
			measureChildren
			
		2、通过子view的规格大小来确定自己的大小 setMeasuredDimession
		
	2、Layout布局过程
     套路和我们Measure类似
     
     
====================华丽丽的分割线============================

Paint画笔的基本使用
1、Paint画笔画图形、路径
2、Paint画笔来画文字
	Paint.FontMetrics
	Paint.FontMetricsInt
	里面有top，ascent，desent, bottom, leading这几个属性
	
	top = top线的y坐标 - baseline线的y坐标
	bottom = bottom线的y坐标 - baseline线的y坐标
	ascent = ascent线的y坐标 - baseline线的y坐标
	desent = desent线的y坐标 - baseline线的y坐标
	
	1、A = B = （bottom - top）/2
	bottom = baseline + FontMetrics.bottom
	top = baseline + FontMetrics.top

	---> A = B = (FontMetrics.bottom - FontMetrics.top)/ 2

	2、C =  B - （bottom - baseline）  =  B - FontMetrics.bottom

	3、C = baseline - center
		
	综上可得
	baseline - center = （FontMetrics.bottom - FontMetrics.top）/2 - FontMetrics.bottom
	baseline = center +（FontMetrics.bottom - FontMetrics.top）/2 - FontMetrics.bottom
