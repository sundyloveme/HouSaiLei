package com.sundy.www.main;

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

	/*
	 * ����ģ��
	 */
	GameSprite spQiu;// ��������
	GameSprite spHouZi;// ���Ӿ���
	GameSprite spTaoZi[] = new GameSprite[6];// ���Ӿ�������
	boolean bookTaoZi[] = new boolean[6];// �����������໥��Ӧ��book���飬��¼�����Ƿ񱻳ԣ�true����
											// falseû��

	/**
	 * ��Ϸ��ʼ
	 */
	@Override
	public void run()
	{

		/*
		 * ��ʼ����������
		 */
		initSpTaoZiArr();

		/*
		 * ��Ϸ�򵥵���Ⱦ
		 */
		GameCore.setGameTitle("������͵��(������)");// ���ر���
		// ���ر���
		GameCore.loadBgView("��ɫ.jpg");

		/*
		 * �������Ӿ���
		 */
		spHouZi = new GameSprite("houzi", 40, GameCore.getGameHeight() - 130);// ���Ӿ���
		spHouZi.playSpriteAnimate("tou");// ���ź���

		/*
		 * ����С����
		 */
		spQiu = new GameSprite("houzi", GameCore.rand(10, 600),
				GameCore.getGameHeight() - 170);

		/*
		 * �߳̿���С����ƶ�
		 */
		GameCore.asyncRun(new Runnable()
		{

			@Override
			public void run()
			{
				// �����ʼ��
				Dir.x = -1;
				Dir.y = -1;

				/*
				 * ����С����(С���������ⲿ��)
				 */
				spQiu.playSpriteAnimate("qiu");// ������

				while (true)
				{
					/*
					 * �߽���
					 */
					if (spQiu.getX() <= 0)// ����߼��
					{
						Dir.x *= -1;
					} else if (spQiu.getY() <= 0)// ��������
					{
						Dir.y *= -1;
					} else if (spQiu.getX() >= GameCore.getGameWidth() - 30)// ���ұ߼��
					{
						Dir.x *= -1;
					}

					spQiu.setPosition(spQiu.getX() + Dir.x, spQiu.getY()
							+ Dir.y);// С����ƶ�������С��λ�ã�

					// ����С����е��ٶ�
					GameCore.pause(5);

					// ����ͷ�������������仯
					if (spQiu.xiangJiao(spHouZi))
					{
						Dir.y *= -1;// Ϊ�����������뻭ͼ����
					}
				}
			}
		});

		/*
		 * ���̼���
		 */
		while (true)
		{
			int kc;// ���������
			kc = GameCore.getPressedKeyCode();

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

			eatTaoZi();// С�������ӵ���ײ��� �ж�С���������Ƿ��ཻ
		}

	}

	/**
	 * С�������ӵ���ײ��� �ж�С���������Ƿ��ཻ
	 */
	public void eatTaoZi()
	{
		for (int i = 0; i < spTaoZi.length; i++)
		{
			if (bookTaoZi[i] == false && spTaoZi[i].xiangJiao(spQiu))
			{
				bookTaoZi[i] = true;
				spTaoZi[i].remove();
				spTaoZi[i] = null;
			}
		}
	}

	/**
	 * ��ʼ����������spTaoZi[]��bookTaoZi����
	 */
	public void initSpTaoZiArr()
	{
		// �����������
		for (int i = 0; i < spTaoZi.length; i++)
		{
			int x = GameCore.rand(0, GameCore.getGameWidth() - 30);
			int y = GameCore.rand(0, GameCore.getGameHeight() / 2);
			spTaoZi[i] = new GameSprite("houzi", x, y);
			spTaoZi[i].playSpriteAnimate("taozi");
		}

		// book����һ�ɳ�ʼ��Ϊfalse
		for (int i = 0; i < bookTaoZi.length; i++)
		{
			bookTaoZi[i] = false;
		}
	}

	/**
	 * ������������Ϸ��
	 */
	public static void main(String[] args)
	{
		GameCore.start(new Main());
	}

}
