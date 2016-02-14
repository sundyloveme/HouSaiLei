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
	 * ����ģ��
	 */
	GameSprite spQiu;// ��������
	GameSprite spHouZi;// ���Ӿ���
	GameSprite spTaoZi[] = new GameSprite[6];// ���Ӿ�������
	boolean bookTaoZi[] = new boolean[6];// �����������໥��Ӧ��book���飬��¼�����Ƿ񱻳ԣ�true����
											// falseû��
	int countEatTaoZi;// �Ե��˶��ٸ�����

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
		 * �������߳� С��ͣ������Ļ�ϵ���
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

					GameCore.pause(5);// ����С����е��ٶ�
				}
			}
		});

		/*
		 * ���̼���
		 */
		while (true)
		{
			int kc = GameCore.getPressedKeyCode();// ���������
			moveTou(kc);// ����kc�����ƺ�ͷ���ƶ�

			// �����ʱ������������С���λ��
			if (spQiu.getY() > GameCore.getGameHeight())
			{
				GameCore.alert("������,��ô�򵥵���Ϸ���᣿");
				spQiu.setPosition(GameCore.rand(10, 600),
						GameCore.getGameHeight() - 170);

				Dir.x = -1;
				Dir.y = -1;// �����ʼ��

			}

			// ����ͷ�������������仯(��������С�򷴵�)
			if (spQiu.xiangJiao(spHouZi))
			{
				// Ϊ�����������뻭ͼ����
				Dir.y *= -1;
			}

			// С�������ӵ���ײ��� �ж�С���������Ƿ��ཻ(�Ե�����)
			if (eatTaoZi())
			{
				countEatTaoZi++;// ���������ӣ��Դ��ж��Ƿ������
			}

			// �ж��Ƿ������
			if (countEatTaoZi == spTaoZi.length)
			{
				GameCore.alert("���ϲ�����Ӯ�ˣ�");
				countEatTaoZi=0;
				initSpTaoZiArr();
			}
		}

	}

	/**
	 * ����kcֵ�ƶ���ͷ
	 * 
	 * @param kc
	 *            ���̰���ֵ
	 */
	public void moveTou(int kc)
	{

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
	}

	/**
	 * С�������ӵ���ײ��� �ж�С���������Ƿ��ཻ
	 * 
	 * @return true ���˸����� falseû�Ե�
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
