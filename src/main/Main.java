package main;

import java.awt.Event;
import java.awt.Point;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;

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
	// 球精灵
	GameSprite spQiu;
	// 猴子精灵
	GameSprite spHouZi;

	/*
	 * Dir对象数组
	 * 为何用数组，因为写错了，要改的地方太多所以不改了
	 */
	Dir dir[] = new Dir[1];

	/**
	 * 将dir数组初始化 为了避免代码混乱，所以新开个函数
	 */
	public void initDir()
	{
		// 为每个数组元素分配个对象
		for (int i = 0; i < dir.length; i++)
		{
			dir[i] = new Dir();
		}

		// 思考后的结果，注意画图
		dir[0].x = -1;
		dir[0].y = -1;
		
	}

	/**
	 * 游戏开始
	 */
	@Override
	public void run()
	{
		// 初始化方向数组
		initDir();

		// 方向监听器
		int kc;

		// 加载标题
		GameCore.setGameTitle("猴赛雷偷桃(￣▽￣)");

		// 加载背景
		GameCore.loadBgView("白色.jpg");

		/*
		 * 猴子精灵模块
		 */
		// 猴子精灵
		spHouZi = new GameSprite("houzi", 40, GameCore.getGameHeight() - 130);
		spHouZi.playSpriteAnimate("tou");// 播放猴子

		/*
		 * 球精灵模块
		 */
		// 球精灵
		spQiu = new GameSprite("houzi", GameCore.rand(10, 600), GameCore.getGameHeight() - 170);
		spQiu.playSpriteAnimate("qiu");// 播放球

		// 键盘监听
		while (true)
		{
			kc = GameCore.getPressedKeyCode();

			// 球开始移动一小步
			moveQiu();

			/*
			 * 根据kc决定精灵的走向
			 */
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
			
			//当猴头碰到球，则方向发生变化
			if(spHouZi.xiangJiao(spQiu))
			{
				dir[0].y*=-1;//为何是这样，请画出分析
			}
		}

	}

	/**
	 * 控制小球的移动
	 */
	public void moveQiu()
	{
		
		if (spQiu.getX() <= 0)
		{
			dir[0].x*=-1;
		} else if (spQiu.getY() <= 0)
		{
			dir[0].y*=-1;
		} else if (spQiu.getX() >= GameCore.getGameWidth()-30)
		{
			dir[0].x*=-1;
		}
		spQiu.setPosition(spQiu.getX() + dir[0].x, spQiu.getY()
				+ dir[0].y);
		
		//控制小球飞行的速度
		GameCore.pause(3);

	}

	public static void main(String[] args)
	{
		GameCore.start(new Main());
	}

}
