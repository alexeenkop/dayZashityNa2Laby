package ua.cn.alexeenkogapon.arkanoid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

public class Plate {
	private double posx;
	private double posy;
	public static final int WIDTH = 80;
	public static final int HEIGHT = 20;

	public Plate(Ball ball) {
		super();
		this.posx = (ball.getSCREEN_WIDTH() - WIDTH) / 2;
		this.posy = ball.getSCREEN_HEIGHT() - HEIGHT;
	}

	public double getPosx() {
		return posx;
	}

	public double getPosy() {
		return posy;
	}

	public void setPos(double x, double y) {
		this.posx = x;
		this.posy = y;
	}

	public void draw(Canvas canvas) {
		Paint p = new Paint();
		p.setColor(Color.GRAY);
		canvas.drawRect((float) posx, (float) posy, (float) (posx + WIDTH),
				(float) (posy + HEIGHT + 40), p);
	}

	public void move(double x, int width) {
		if ((x >= 40) && (x <= width - 40)) {
			this.posx = x - 40;
		} else if (x < 40) {
			this.posx = 0;
		} else if (x > width - 40) {
			this.posx = width - 80;
		}

	}

	public void checkHit(Ball ball) {
		double bpx = ball.getPosx();
		double bpy = ball.getPosy();
		if ((bpx >= posx) && (bpx <= posx + WIDTH / 5)
				&& (bpy + ball.getRadius() >= posy)
				&& (bpy + ball.getRadius() < posy + HEIGHT)) {// |***|---|---|---|---|
																// проверка
			ball.BallVector(ball.getVx() - 3, ball.getVy() - 1);
		} else if ((bpx > posx + WIDTH / 5) && (bpx <= posx + 2*(WIDTH / 5))
				&& (bpy + ball.getRadius() >= posy)
				&& (bpy + ball.getRadius() < posy + HEIGHT)) {// |---|***|---|---|---|
																// проверка
			ball.BallVector(ball.getVx() - 2, ball.getVy() - 1);
		} else if ((bpx > posx + 2*(WIDTH / 5)) && (bpx < posx + 3*(WIDTH / 5))
				&& (bpy + ball.getRadius() >= posy)
				&& (bpy + ball.getRadius() < posy + HEIGHT)) {// |---|---|***|---|---|
																// проверка
			if (ball.getVx() > 0) {
				ball.BallVector(0.3, -ball.getVy());
			}else {
				ball.BallVector(-0.3, -ball.getVy());
			}
			
		} else if ((bpx >= posx + 3*(WIDTH / 5)) && (bpx < posx + 4*(WIDTH / 5))
				&& (bpy + ball.getRadius() >= posy)
				&& (bpy + ball.getRadius() < posy + HEIGHT)) {// |---|---|---|***|---|
																// проверка
			ball.BallVector(ball.getVx() + 2, ball.getVy() - 1);
		} else if ((bpx >= posx + 4*(WIDTH / 5)) && (bpx <= posx + WIDTH)
				&& (bpy + ball.getRadius() >= posy)
				&& (bpy + ball.getRadius() < posy + HEIGHT)) {// |---|---|---|---|***|
																// проверка
			ball.BallVector(ball.getVx() + 3, ball.getVy() - 1);
		} else
		// отскакиваем от верхней границы
		// if ((bpy + ball.getRadius() >= posy)
		// && (bpy + ball.getRadius() < posy + HEIGHT)
		// && (bpx >= posx) && (bpx <= posx + WIDTH)) {
		// ball.setVector(ball.getVx(), -ball.getVy());
		// } else
		// отскакиваем от нижней границы
		if ((bpy - ball.getRadius() > posy)
				&& (bpy - ball.getRadius() <= posy + HEIGHT) && (bpx >= posx)
				&& (bpx <= posx + WIDTH)) {
			ball.BallVector(ball.getVx(), -ball.getVy());
		} else
		// отскакиваем от левой границы
		if ((bpx + ball.getRadius() >= posx)
				&& (bpx + ball.getRadius() < posx + WIDTH) && (bpy >= posy)
				&& (bpy <= posy + HEIGHT)) {
			ball.BallVector(-ball.getVx(), ball.getVy());
		} else
		// отскакиваем от правой границы
		if ((bpx - ball.getRadius() > posx)
				&& (bpx - ball.getRadius() <= posx + WIDTH) && (bpy >= posy)
				&& (bpy <= posy + HEIGHT)) {
			ball.BallVector(-ball.getVx(), ball.getVy());
		}
	}

}
