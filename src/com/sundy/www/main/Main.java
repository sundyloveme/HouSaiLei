package com.sundy.www.main;

import java.awt.Event;
import java.awt.Point;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.rupeng.game.GameCore;
import com.sundy.www.gamecore.GameImage;
import com.sundy.www.gamecore.GameSprite;
import com.sundy.www.gamecore.GameSpriteCoin;
import com.sundy.www.gamecore.GameText;

/**
 * 
 * @author Sundy
 * 
 */
public class Main implements Runnable
{

	/*
	 * 定义模块
	 */
	GameSprite spQiu;// 定义球精灵
	GameSprite spHouZi;// 猴子精灵
	GameSprite spTaoZi[] = new GameSprite[6];// 桃子精灵数组
	boolean bookTaoZi[] = new boolean[6];// 与桃子数组相互对应的book数组，记录桃子是否被吃，true吃了
											// false没吃
	int countEatTaoZi;// 吃掉了多少个桃子

	/**
	 * 游戏开始
	 */
	@Override
	public void run()
	{

		/*
		 * 初始化桃子数组
		 */
		initSpTaoZiArr();

		/*
		 * 游戏简单的渲染
		 */
		GameCore.setGameTitle("猴赛雷偷桃(￣▽￣)");// 加载标题
		// 加载背景
		GameCore.loadBgView("白色.jpg");

		/*
		 * 创建猴子精灵
		 */
		spHouZi = new GameSprite("houzi", 40, GameCore.getGameHeight() - 130);// 猴子精灵
		spHouZi.playSpriteAnimate("tou");// 播放猴子

		/*
		 * 创建小球精灵
		 */
		spQiu = new GameSprite("houzi", GameCore.rand(10, 600),
				GameCore.getGameHeight() - 170);

		/*
		 * 匿名类线程 小球不停的在屏幕上弹射
		 */
		GameCore.asyncRun(new Runnable()
		{

			@Override
			public void run()
			{
				// 方向初始化
				Dir.x = -1;
				Dir.y = -1;

				/*
				 * 创建小球精灵(小球定义在了外部类)
				 */
				spQiu.playSpriteAnimate("qiu");// 播放球

				while (true)
				{
					/*
					 * 边界检测
					 */
					if (spQiu.getX() <= 0)// 最左边检测
					{
						Dir.x *= -1;
					} else if (spQiu.getY() <= 0)// 最上面检测
					{
						Dir.y *= -1;
					} else if (spQiu.getX() >= GameCore.getGameWidth() - 30)// 最右边检测
					{
						Dir.x *= -1;
					}

					spQiu.setPosition(spQiu.getX() + Dir.x, spQiu.getY()
							+ Dir.y);// 小球的移动（重置小球位置）

					GameCore.pause(5);// 控制小球飞行的速度
				}
			}
		});

		/*
		 * 键盘监听
		 */
		while (true)
		{
			int kc = GameCore.getPressedKeyCode();// 方向监听器
			moveTou(kc);// 根据kc来控制猴头的移动

			// 球落地时，死亡。重置小球的位置
			if (spQiu.getY() > GameCore.getGameHeight())
			{
				GameCore.alert("你死啦,这么简单的游戏不会？");
				spQiu.setPosition(GameCore.rand(10, 600),
						GameCore.getGameHeight() - 170);

				Dir.x = -1;
				Dir.y = -1;// 方向初始化

			}

			// 当猴头碰到球，则方向发生变化(板子碰到小球反弹)
			if (spQiu.xiangJiao(spHouZi))
			{
				// 为何是这样，请画图分析
				Dir.y *= -1;
			}

			// 小球与桃子的碰撞检测 判断小球与桃子是否相交(吃到桃子)
			if (eatTaoZi())
			{
				countEatTaoZi++;// 计数器增加，以此判断是否吃完了
			}

			// 判断是否吃完了
			if (countEatTaoZi == spTaoZi.length)
			{
				GameCore.alert("哎呦不错，你赢了！");
				countEatTaoZi=0;
				initSpTaoZiArr();
			}
		}

	}

	/**
	 * 根据kc值移动猴头
	 * 
	 * @param kc
	 *            键盘按键值
	 */
	public void moveTou(int kc)
	{

		switch (kc)
		{
		case KeyEvent.VK_RIGHT:
			// spHouzi的x坐标不能超过窗体的宽度
			if (spHouZi.getX() < GameCore.getGameWidth() - 110)
			{
				spHouZi.moveRight();
				spHouZi.moveRight();
				spHouZi.moveRight();
				spHouZi.moveRight();
				spHouZi.moveRight();// 移动五次，为了加快速度
				break;
			}
			break;
		case KeyEvent.VK_LEFT:
			// spHouzi的x坐标不能小于2
			if (spHouZi.getX() > 2)
			{
				spHouZi.moveLeft();
				spHouZi.moveLeft();
				spHouZi.moveLeft();
				spHouZi.moveLeft();
				spHouZi.moveLeft();// 移动五次，为了加快速度
				break;
			}
			break;
		}
	}

	/**
	 * 小球与桃子的碰撞检测 判断小球与桃子是否相交
	 * 
	 * @return true 吃了个桃子 false没吃到
	 */
	public boolean eatTaoZi()
	{
		for (int i = 0; i < spTaoZi.length; i++)
		{
			if (bookTaoZi[i] == false && spTaoZi[i].xiangJiao(spQiu))
			{
				bookTaoZi[i] = true;
				spTaoZi[i].remove();
				spTaoZi[i] = null;

				return true;
			}
		}
		return false;
	}

	/**
	 * 初始化桃子数组spTaoZi[]与bookTaoZi数组
	 */
	public void initSpTaoZiArr()
	{
		// 随机生成桃子
		for (int i = 0; i < spTaoZi.length; i++)
		{
			int x = GameCore.rand(0, GameCore.getGameWidth() - 30);
			int y = GameCore.rand(0, GameCore.getGameHeight() / 2);
			spTaoZi[i] = new GameSprite("houzi", x, y);
			spTaoZi[i].playSpriteAnimate("taozi");
		}

		// book数组一律初始化为false
		for (int i = 0; i < bookTaoZi.length; i++)
		{
			bookTaoZi[i] = false;
		}
	}

	/**
	 * 主方法启动游戏器
	 */
	public static void main(String[] args)
	{
		GameCore.start(new Main());
	}

}
