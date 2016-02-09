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
	// ����
	GameSprite spQiu;
	// ���Ӿ���
	GameSprite spHouZi;

	/*
	 * Dir��������
	 * Ϊ�������飬��Ϊд���ˣ�Ҫ�ĵĵط�̫�����Բ�����
	 */
	Dir dir[] = new Dir[1];

	/**
	 * ��dir�����ʼ�� Ϊ�˱��������ң������¿�������
	 */
	public void initDir()
	{
		// Ϊÿ������Ԫ�ط��������
		for (int i = 0; i < dir.length; i++)
		{
			dir[i] = new Dir();
		}

		// ˼����Ľ����ע�⻭ͼ
		dir[0].x = -1;
		dir[0].y = -1;
		
	}

	/**
	 * ��Ϸ��ʼ
	 */
	@Override
	public void run()
	{
		// ��ʼ����������
		initDir();

		// ���������
		int kc;

		// ���ر���
		GameCore.setGameTitle("������͵��(������)");

		// ���ر���
		GameCore.loadBgView("��ɫ.jpg");

		/*
		 * ���Ӿ���ģ��
		 */
		// ���Ӿ���
		spHouZi = new GameSprite("houzi", 40, GameCore.getGameHeight() - 130);
		spHouZi.playSpriteAnimate("tou");// ���ź���

		/*
		 * ����ģ��
		 */
		// ����
		spQiu = new GameSprite("houzi", GameCore.rand(10, 600), GameCore.getGameHeight() - 170);
		spQiu.playSpriteAnimate("qiu");// ������

		// ���̼���
		while (true)
		{
			kc = GameCore.getPressedKeyCode();

			// ��ʼ�ƶ�һС��
			moveQiu();

			/*
			 * ����kc�������������
			 */
			switch (kc)
			{
			case KeyEvent.VK_RIGHT:
				// spHouzi��x���겻�ܳ�������Ŀ��
				if (spHouZi.getX() < GameCore.getGameWidth() - 110)
				{
					spHouZi.moveRight();
					spHouZi.moveRight();
					spHouZi.moveRight();
					spHouZi.moveRight();
					spHouZi.moveRight();// �ƶ���Σ�Ϊ�˼ӿ��ٶ�
					break;
				}
				break;
			case KeyEvent.VK_LEFT:
				// spHouzi��x���겻��С��2
				if (spHouZi.getX() > 2)
				{
					spHouZi.moveLeft();
					spHouZi.moveLeft();
					spHouZi.moveLeft();
					spHouZi.moveLeft();
					spHouZi.moveLeft();// �ƶ���Σ�Ϊ�˼ӿ��ٶ�
					break;
				}
				break;
			}
			
			//����ͷ�������������仯
			if(spHouZi.xiangJiao(spQiu))
			{
				dir[0].y*=-1;//Ϊ�����������뻭������
			}
		}

	}

	/**
	 * ����С����ƶ�
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
		
		//����С����е��ٶ�
		GameCore.pause(3);

	}

	public static void main(String[] args)
	{
		GameCore.start(new Main());
	}

}
