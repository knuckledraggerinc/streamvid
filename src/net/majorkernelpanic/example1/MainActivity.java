package net.majorkernelpanic.example1;

import net.majorkernelpanic.example1.PrefsActivity;
import net.majorkernelpanic.streaming.SessionBuilder;
import net.majorkernelpanic.streaming.gl.SurfaceView;
import net.majorkernelpanic.streaming.rtsp.RtspServer;
import net.majorkernelpanic.example1.PrefsActivity;
import net.majorkernelpanic.streaming.video.VideoQuality;
import net.majorkernelpanic.example1.VideoRecording;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;
import android.net.Uri;

/**
 * A straightforward example of how to use the RTSP server included in libstreaming.
 */
public class MainActivity extends Activity {

	private final static String TAG = "MainActivity";

	private SurfaceView mSurfaceView;

	boolean recording;
	//private Camera myCamera;
	//private MediaRecorder mediaRecorder;
	SurfaceHolder surfaceHolder;
	Button myButton;
	
	final static int REQUEST_VIDEO_CAPTURED = 1;
	Uri uriVideo = null;
	VideoView videoviewPlay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.activity_main);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		Button myButton = (Button)findViewById(R.id.myButton);
        
		myButton.setOnClickListener(new Button.OnClickListener(){

			 @Override
			 public void onClick(View arg0) {
			  // TODO Auto-generated method stub
			  
				 
				 Intent intent = new Intent(android.provider.MediaStore.ACTION_VIDEO_CAPTURE);
			  startActivityForResult(intent, REQUEST_VIDEO_CAPTURED);
			 }});
      
				 
				 
				 
				 
        Button btnPrefs = (Button) findViewById(R.id.btnPrefs);
		btnPrefs.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(MainActivity.this, PrefsActivity.class);
				startActivity(intent);
			}
		});
			
		
		mSurfaceView = (SurfaceView) findViewById(R.id.surface);

		
		
		// Sets the port of the RTSP server to 1234
		Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
		editor.putString(RtspServer.KEY_PORT, String.valueOf(1234));
		editor.commit();

		
		
		
		
		// Configures the SessionBuilder
		SessionBuilder.getInstance()
		.setSurfaceView(mSurfaceView)
		.setPreviewOrientation(90)
		.setContext(getApplicationContext())
		//.setAudioEncoder(SessionBuilder.AUDIO_NONE)
		.setAudioEncoder(SessionBuilder.AUDIO_AMRNB)
		.setVideoEncoder(SessionBuilder.VIDEO_H264);
		
		
		
		// Starts the RTSP server
		this.startService(new Intent(this,RtspServer.class));

	}
	

}
