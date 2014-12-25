package jp.takesin.binarynumber;

import java.util.Calendar;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final String TAG = MainActivity.class.getSimpleName();

	private int mOnImg = R.drawable.black;
	private int mOffImg = R.drawable.white;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	private static int TIMEOUT_MESSAGE = 1;
	private Handler mHandler = new Handler() {
		@Override
		public void dispatchMessage(Message msg) {
			if (msg.what == TIMEOUT_MESSAGE && mHandler != null) {
				updateTime();
				mHandler.sendEmptyMessageDelayed(TIMEOUT_MESSAGE, 1000);
			} else {
				super.dispatchMessage(msg);
			}
		}
	};
	
	@Override
	protected void onResume() {
		super.onResume();
		updateTime();
		mHandler.sendEmptyMessage(TIMEOUT_MESSAGE);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mHandler = null;
	}

	private void updateTime() {
		final Calendar cal = Calendar.getInstance();
		final int year = cal.get(Calendar.YEAR);
		final int month = cal.get(Calendar.MONTH);
		final int day = cal.get(Calendar.DAY_OF_MONTH);
		final int hour = cal.get(Calendar.HOUR_OF_DAY);
		final int minute = cal.get(Calendar.MINUTE);
		final int second = cal.get(Calendar.SECOND);
		
		
		StringBuffer buf = new StringBuffer();
		buf.append(year);
		buf.append("/");
		buf.append(month);
		buf.append("/");
		buf.append(day);
		buf.append(" ");
		buf.append(hour);
		buf.append(":");
		buf.append(minute);
		buf.append(":");
		buf.append(second);

		final TextView dayTxt = (TextView) findViewById(R.id.textDays);
		dayTxt.setText(buf);

		setBinaryTime(cal);
	}

	private void setBinaryTime(Calendar cal) {

		final int hour = cal.get(Calendar.HOUR_OF_DAY);
		setBinaryView(hour, TYPE_HOUR);

		final int minute = cal.get(Calendar.MINUTE);
		setBinaryView(minute, TYPE_MINUTE);

		final int second = cal.get(Calendar.SECOND);
		setBinaryView(second, TYPE_SECOND);

	}

	private static final int TYPE_HOUR = 0;
	private static final int TYPE_MINUTE = 1;
	private static final int TYPE_SECOND = 2;

	private void setBinaryView(int time, int type) {
		ImageView imgView1 = null;
		ImageView imgView2 = null;
		ImageView imgView3 = null;
		ImageView imgView4 = null;
		ImageView imgView5 = null;
		ImageView imgView6 = null;
		if (type == TYPE_HOUR) {
			imgView1 = (ImageView) findViewById(R.id.ImgHour1);
			imgView2 = (ImageView) findViewById(R.id.ImgHour2);
			imgView3 = (ImageView) findViewById(R.id.ImgHour3);
			imgView4 = (ImageView) findViewById(R.id.ImgHour4);
			imgView5 = (ImageView) findViewById(R.id.ImgHour5);
			imgView6 = (ImageView) findViewById(R.id.ImgHour6);
		} else if (type == TYPE_MINUTE) {
			imgView1 = (ImageView) findViewById(R.id.ImgMinute1);
			imgView2 = (ImageView) findViewById(R.id.ImgMinute2);
			imgView3 = (ImageView) findViewById(R.id.ImgMinute3);
			imgView4 = (ImageView) findViewById(R.id.ImgMinute4);
			imgView5 = (ImageView) findViewById(R.id.ImgMinute5);
			imgView6 = (ImageView) findViewById(R.id.ImgMinute6);
		} else if (type == TYPE_SECOND) {
			imgView1 = (ImageView) findViewById(R.id.ImgSecond1);
			imgView2 = (ImageView) findViewById(R.id.ImgSecond2);
			imgView3 = (ImageView) findViewById(R.id.ImgSecond3);
			imgView4 = (ImageView) findViewById(R.id.ImgSecond4);
			imgView5 = (ImageView) findViewById(R.id.ImgSecond5);
			imgView6 = (ImageView) findViewById(R.id.ImgSecond6);
		}

		String binaryHour = Integer.toBinaryString(time);

		final int length = 6 - binaryHour.length();
		for (int i = 0; i < length; i++) {
			binaryHour = "0" + binaryHour;
		}

		switch (binaryHour.length()) {
		case 6:
			if (TextUtils.equals("1", binaryHour.substring(5, 6))) {
				imgView1.setImageResource(mOnImg);
			} else {
				imgView1.setImageResource(mOffImg);
			}
		case 5:
			if (TextUtils.equals("1", binaryHour.substring(4, 5))) {

				imgView2.setImageResource(mOnImg);
			} else {
				imgView2.setImageResource(mOffImg);
			}
		case 4:
			if (TextUtils.equals("1", binaryHour.substring(3, 4))) {
				imgView3.setImageResource(mOnImg);
			} else {
				imgView3.setImageResource(mOffImg);
			}
		case 3:
			if (TextUtils.equals("1", binaryHour.substring(2, 3))) {
				imgView4.setImageResource(mOnImg);
			} else {
				imgView4.setImageResource(mOffImg);
			}
		case 2:
			if (TextUtils.equals("1", binaryHour.substring(1, 2))) {
				imgView5.setImageResource(mOnImg);
			} else {
				imgView5.setImageResource(mOffImg);
			}
		case 1:
			if (TextUtils.equals("1", binaryHour.substring(0, 1))) {
				imgView6.setImageResource(mOnImg);
			} else {
				imgView6.setImageResource(mOffImg);
			}
			break;

		default:
			break;
		}
	}


}
