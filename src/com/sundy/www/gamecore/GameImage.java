package com.sundy.www.gamecore;

import java.awt.Dimension;
import java.awt.Point;

import com.rupeng.game.GameCore;

public class GameImage extends GameRect
{

	// ͼƬ�ı��
	private int ImageNum;

	/**
	 * ���캯��
	 * 
	 * @param imgName
	 *            ͼƬ����
	 */
	public GameImage(String imgName)
	{
		this.ImageNum = NumberManager.instance.getNum();
		GameCore.createImage(this.ImageNum, imgName);
	}

	/**
	 * ȱʡ���캯��
	 */
	public GameImage()
	{
		this("");
	}

	/**
	 * ��ʾͼƬ
	 */
	@Override
	public void show()
	{
		GameCore.showImage(this.ImageNum);
	}

	/**
	 * ����ͼƬ
	 */
	@Override
	public void hide()
	{
		GameCore.hideImage(ImageNum);
	}

	/**
	 * �������
	 */
	@Override
	public Point getPosition()
	{
		return GameCore.getImagePosition(ImageNum);
	}

	/**
	 * ��������
	 */
	@Override
	public void setPosition(Point pos)
	{
		GameCore.setImagePosition(ImageNum, pos.x, pos.y);
	}

	/**
	 * ��������
	 */
	@Override
	public void setPosition(int x, int y)
	{
		GameCore.setImagePosition(ImageNum, x, y);
	}

	/**
	 * ���x
	 */
	@Override
	public int getX()
	{
		return GameCore.getImageX(ImageNum);
	}

	/**
	 * ���y
	 */
	@Override
	public int getY()
	{
		return GameCore.getImageY(ImageNum);
	}

	/**
	 * ����ͼƬ����
	 * 
	 * @param imgName
	 *            ͼƬ����
	 */
	public void setImgName(String imgName)
	{
		GameCore.setImageSource(this.ImageNum, imgName);
	}

	/**
	 * �Ƴ�ͼƬ
	 */
	public void remove()
	{
		GameCore.removeImage(this.ImageNum);
	}

	@Override
	public Dimension getSize()
	{
		return GameCore.getImageSize(ImageNum);
	}

	@Override
	public int getHeight()
	{
		return GameCore.getImageHeight(ImageNum);
	}

	@Override
	public int getWidth()
	{
		return GameCore.getImageWidth(ImageNum);
	}

}
