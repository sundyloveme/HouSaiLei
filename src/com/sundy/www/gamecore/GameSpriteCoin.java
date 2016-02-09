package com.sundy.www.gamecore;

import java.awt.Dimension;
import java.awt.Point;

import com.rupeng.game.GameCore;

/**
 * һ������࣬�̳��뾫����
 * @author Sundy
 *
 */
public class GameSpriteCoin 
{
	//�Ƿ񱻳��� falseû�� true����
	private boolean book;
	private GameSprite spCoin;
	
	/**
	 * ���캯��
	 */
	public GameSpriteCoin(int x,int y)
	{
		spCoin =new GameSprite("coin", x, y);
		spCoin.playSpriteAnimate("rotate");
	}

	/**
	 * ���ؽ�Ҷ���
	 * @return
	 */
	public GameSprite getSpcoin()
	{
		return spCoin;
	}
	
	/**
	 * �Ƿ񱻳���
	 * true ����
	 * false û����
	 * @return book
	 */
	public boolean getBook()
	{
		return book;
	}

	/**
	 * �ý�ұ����� 
	 */
	public void eat()
	{
		this.book = true;
	}
}
