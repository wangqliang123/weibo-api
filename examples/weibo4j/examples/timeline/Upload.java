package weibo4j.examples.timeline;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import weibo4j.Timeline;
import weibo4j.Weibo;
import weibo4j.http.ImageItem;
import weibo4j.model.Status;


public class Upload {
	public static void main(String args[]){
		try{
			Weibo weibo = new Weibo();
			weibo.setToken(args[0]);
			try{
				byte[] content= readFileImage("psu.jpg");
				System.out.println("content length:" + content.length);
				ImageItem pic=new ImageItem("pic",content);

				String s=java.net.URLEncoder.encode( args[1],"utf-8");
				Timeline tl = new Timeline();
				Status status=tl.UploadStatus(s, pic);

				System.out.println("Successfully upload the status to ["
						+status.getText()+"].");
			}
			catch(Exception e1){
				e1.printStackTrace();
			}
		}catch(Exception ioe){
			System.out.println("Failed to read the system input.");
		}
	}
	public static byte[] readFileImage(String filename)throws IOException{
		BufferedInputStream bufferedInputStream=new BufferedInputStream(
				new FileInputStream(filename));
		int len =bufferedInputStream.available();
		byte[] bytes=new byte[len];
		int r=bufferedInputStream.read(bytes);
		if(len !=r){
			bytes=null;
			throw new IOException("Exception");
		}
		bufferedInputStream.close();
		return bytes;
	}
}
