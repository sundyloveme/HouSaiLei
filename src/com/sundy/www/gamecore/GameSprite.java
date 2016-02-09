package com.sundy.www.gamecore;

import java.awt.Dimension;
import java.awt.Point;

import com.rupeng.game.GameCore;

public class GameSprite extends GameRect
{
	//����ı��
	private int spriteNum;
	
	/**
	 * ���캯��
	 * @param spriteName ��������
	 */
	public GameSprite(String spriteName)
	{
		this.spriteNum=NumberManager.instance.getNum();
		GameCore.createSprite(this.spriteNum, spriteName);
	}
	
	/**
	 * ���캯��
	 * @param spriteName ��������
	 * @param x x����
	 * @param y y����
	 */
	public GameSprite(String spriteName,int x,int y)
	{
		this(spriteName);
		GameCore.setSpritePosition(this.spriteNum, x, y);
	}
	
	/**
	 * ��ʾ����
	 */
	@Override
	public void show()
	{
		GameCore.showSprite(this.spriteNum);
	}

	/**
	 * ���ؾ���
	 */
	@Override
	public void hide()
	{
		GameCore.hideSprite(this.spriteNum);
	}

	/**
	 * �õ�����
	 */
	@Override
	public Point getPosition()
	{
		return GameCore.getSpritePosition(this.spriteNum);
	}

	/**
	 * ��������
	 */
	@Override
	public void setPosition(Point pos)
	{
		GameCore.setSpritePosition(this.spriteNum, pos.x, pos.y);
	}

	/**
	 * ��������
	 */
	@Override
	public void setPosition(int x, int y)
	{
		GameCore.setSpritePosition(this.spriteNum, x, y);
	}

	/**
	 * ���x
	 */
	@Override
	public int getX()
	{
		return GameCore.getSpriteX(this.spriteNum);
	}

	/**
	 * ���y
	 */
	@Override
	public int getY()
	{
		return GameCore.getSpriteY(this.spriteNum);
	}
	
	/**
	 * ���Ŷ���
	 * @param animateName ��������
	 */
	public void playSpriteAnimate(String animateName)
	{
		GameCore.playSpriteAnimate(this.spriteNum, animateName, true);
	}
	
	/**
	 * ��ø߶�
	 * @return
	 */
	@Override
	public int getHeight()
	{
		return GameCore.getSpriteHeight(spriteNum);
	}
	
	/**
	 * ��ÿ��
	 * @return
	 */
	@Override
	public int getWidth()
	{
		return GameCore.getSpriteWidth(spriteNum);
	}
	
	/**
	 * �Ƴ�����
	 */
	public void remove()
	{
		GameCore.removeSprite(spriteNum);
	}
	
	/**
	 * ��x�ᷴת
	 * @param flipX
	 */
	public void setFlipX(boolean flipX)
	{
		GameCore.setSpriteFlipX(spriteNum, flipX);
	}
	
	/**
	 * ��y�ᷴת
	 * @param flipY
	 */
	public void setFlipY(boolean flipY)
	{
		GameCore.setSpriteFlipY(spriteNum, flipY);
	}
	

	@Override
	public Dimension getSize()
	{
		return GameCore.getSpriteSize(spriteNum);
	}

	
}
