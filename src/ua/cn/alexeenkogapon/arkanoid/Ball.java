package ua.cn.alexeenkogapon.arkanoid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.widget.Toast;

public class Ball {
	private double posx;
	private double posy;
	private double radius = 10;
	private double vx = 0;// вектор направления движения
	private double vy = 0;// вектор направления движения
	private int SCREEN_WIDTH;
	private int SCREEN_HEIGHT;
	private int speed = 1;
	private int life = 3;// счетчик жизней
	private int hits = 0;// счетчик количества ударов

	public Ball(double x, double y) {
		super();
		this.posx = x;
		this.posy = y;
	}
	public void setPos(double x, double y) {
		this.posx = x;
		this.posy = y;
	}
	public double getPosx() {
		return posx;
	}
	public double getPosy() {
		return posy;
	}
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
	public void decrLife() {
		this.life--;
	}
	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public void setVector(double vx, double vy) {
		this.vx = vx;
		this.vy = vy;
	}

	public double getVx() {
		return vx;
	}

	public double getVy() {
		return vy;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public void resetSpeed() {
		this.speed = 1;
	}

	public void incSpeed() {
		if (speed < 15) {
			this.speed += 1;
		}
	}

	public int getSCREEN_WIDTH() {
		return SCREEN_WIDTH;
	}

	public void setSCREEN_WIDTH(int sCREEN_WIDTH) {
		SCREEN_WIDTH = sCREEN_WIDTH;
	}

	public int getSCREEN_HEIGHT() {
		return SCREEN_HEIGHT;
	}

	public void setSCREEN_HEIGHT(int sCREEN_HEIGHT) {
		SCREEN_HEIGHT = sCREEN_HEIGHT;
	}

	public void setScreenSize(Canvas canvas) {
		/*
		 * узнаем размеры экрана
		 */
		setSCREEN_WIDTH(canvas.getWidth());
		setSCREEN_HEIGHT(canvas.getHeight());
	}
	
	public void BallVector(double x, double y)
	{
	    vx=x;
	    vy=y;

	    // Теперь нужно превратить наш вектор в единичную длину, сначала найдём длину вектора
	    // Это корень квадратный из суммы компонентов вектора, которые в свою очередь были возведены в квадрат
	    double len=Math.sqrt(vx*vx+vy*vy);

	    // Теперь надо каждый компонент разделить на длину (это называется нормализация вектора)
	    vx/=len;
	    vy/=len;
	}
	
	public void move() {
		if (hits == 3) {// если количество убитых блока = 3
			incSpeed();// увеличиваем скорость
			setHits(0);// обнуляем счетчик
		}
		setPos(posx + vx * speed, posy + vy * speed);// передвигаем шар
		if (posx <= getRadius()) {
			BallVector(-vx, vy);
			//setVector(-vx, vy); // отскакиваем от левой стены
		}
		if (posx >= getSCREEN_WIDTH() - getRadius()) {
			BallVector(-vx, vy);
			//setVector(-vx, vy); // и от правой
		}
		if (posy <= getRadius()) {
			BallVector(vx, -vy);
			//setVector(vx, -vy); // и от потолка
		}

		if (posy >= getSCREEN_HEIGHT() + getRadius()){
			//setPos(getSCREEN_WIDTH() / 2, getSCREEN_HEIGHT() - getRadius() - Plate.getHeight() - 1);
			//setVector(0, 0);
			decrLife();
			if (this.life == 0) {
				
			}
		}
	}

	public void draw(Canvas canvas) {
		Paint p = new Paint();
		p.setColor(Color.BLUE);
		canvas.drawCircle((float) posx, (float) posy, (float) getRadius(), p);
	}

	public void pointsOfTouch() {
		// double x = getRadius() * Math.cos(45);
		// double y = getRadius() * Math.sin(45);
	}

	public void incrHits() {
		hits += 1;
	}

	public int getSpeed() {
		return speed;
	}

}
