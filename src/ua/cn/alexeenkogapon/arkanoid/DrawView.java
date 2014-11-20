package ua.cn.alexeenkogapon.arkanoid;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawView extends SurfaceView implements SurfaceHolder.Callback{

		private DrawThread drawThread;
		public Game game = new Game();

		public DrawView(Context context) {
			super(context);
			getHolder().addCallback(this);
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
		}

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			drawThread = new DrawThread(getHolder());
			drawThread.setRunning(true);
			drawThread.start();
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			boolean retry = true;
			drawThread.setRunning(false);
			while (retry) {
				try {
					drawThread.join();
					retry = false;
				} catch (InterruptedException e) {
				}
			}
		}

		class DrawThread extends Thread {

			private boolean running = false;
			private SurfaceHolder surfaceHolder;

			public DrawThread(SurfaceHolder surfaceHolder) {
				this.surfaceHolder = surfaceHolder;
			}

			public void setRunning(boolean running) {
				this.running = running;
			}

			@Override
			public void run() {
				List<Block> block = new LinkedList<Block>();
				Ball ball = new Ball(1, 1);// наш шарик
				ball.setVector(-1, -1);// задаем начальное направление движения
				Plate plate = null;
				Paint p = new Paint();
				Canvas canvas;
				while (running) {
					canvas = null;
					try {
						canvas = surfaceHolder.lockCanvas(null);
						if (canvas == null)
							continue;
						ball.setScreenSize(canvas);// берем размеры канвы
						if (plate == null) {
							plate = new Plate(ball);
						}
						if (!game.init) {
							ball.setPos((int)(canvas.getWidth() / 2),(int)(canvas.getHeight() - ball.getRadius() - plate.HEIGHT - 1));// помещаем
																// шарик внизу
																// экрана
						}
						game.initBlocks(block, ball);
						canvas.drawColor(Color.WHITE);
						for (Block block2 : block) {
							block2.draw(canvas);// отрисовываем блоки
							block2.checkHit(ball);// проверяем на
													// соприкосновение шарика с
													// блоком
							if (block2.isDead()) {
								game.cntBlocks++;// кол-во убитых блоков
							}
						}
						plate.draw(canvas);
						plate.checkHit(ball);
						ball.draw(canvas);// рисуем шарик
						if (ball.getLife() == 0) {//если жизней нет
							ball.setLife(3);
						}
						game.drawStatistic(canvas, ball);
						
						
						if (MainActivity.OrientationData[2] >= -0.1 && MainActivity.OrientationData[2] <= 0.1) {
							plate.move(plate.getPosx(), canvas.getWidth());
						}else if (MainActivity.OrientationData[2] < -0.1 && MainActivity.OrientationData[2] > -0.6) {
							plate.move(plate.getPosx()-MainActivity.OrientationData[2]*5, canvas.getWidth());
						}else if (MainActivity.OrientationData[2] <= -0.6 && MainActivity.OrientationData[2] >= -1.2) {
							plate.move(plate.getPosx()-MainActivity.OrientationData[2]*10, canvas.getWidth());
						}else if (MainActivity.OrientationData[2] > 0.1 && MainActivity.OrientationData[2] < 0.6) {
							plate.move(plate.getPosx()+MainActivity.OrientationData[2]*5, canvas.getWidth());
						}else if (MainActivity.OrientationData[2] >= 0.6 && MainActivity.OrientationData[2] <= 1.2) {
							plate.move(plate.getPosx()+MainActivity.OrientationData[2]*10, canvas.getWidth());
						}
						//plate.move(ball.getPosx(), canvas.getWidth());
						
						ball.move();// передвигаем шарик
					} finally {
						if (canvas != null) {
							surfaceHolder.unlockCanvasAndPost(canvas);
						}
					}
				}
			}
		}

	
}
