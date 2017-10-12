import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.border.*;

public class FComponent extends JComponent{
		
		 int nFace,nVert;
		 int [][] face;
		 double[][] vert;
		 int denorm;
		 int dim,iter;
		 double lightX,lightY,lightZ;
		 double[][] Norm;
		 int R,G,B;
		 boolean wireframe;
    	int[][] screenVert;
    	double[] NoV;
    	double[] NoL;
    	double[] NoH;
    	int[] Zsort;
   		Graphics2D big;
		BufferedImage bi;
		int centerX,centerY,w,h;
		 Graphics2D g2;
	public void FComponent(int nFace1,int nVert1,double[][] vert1,int[][] face1,int denorm1, double lightX1,double lightY1,double lightZ1,int iter1,int dim1,int R1,int B1,int G1,boolean wireframe1){
	nFace=nFace1;nVert=nVert1;vert=vert1;face=face1;dim=dim1;iter=iter1;denorm=denorm1;lightX=lightX1;lightY=lightY1;lightZ=lightZ1;R=R1;B=B1;G=G1;wireframe=wireframe1;
		    Norm=new double[nFace][3];
    	screenVert=new int[nVert][2];
    	 NoV=new double[nFace];
    	 NoL=new double[nFace];
    	 NoH=new double[nFace];
    	 Zsort = new int[nVert];
		  w=this.getWidth();h=this.getHeight();
		  centerX=(int)(w/2);centerY=(int)(h/2);
		  g2=(Graphics2D)this.getGraphics();
		   bi=(BufferedImage)this.createImage(w,h);
		   big=bi.createGraphics();	
		 
	}
			
public void paint() {

		   System.out.println("eneter paint");
		   big.clearRect(0, 0, w, h);
		   big.setColor(new Color(255,255,255));
		   big.fillRect(0,0,w,h);
		   big.setColor(new Color(0,0,0)); 
			big.drawLine(centerX,0,centerX,h);
			big.drawLine(0,centerY,w,centerY);		
			////////////finding normal
		for(int i=0;i<nFace;i++){
		 Norm[i][0] = (vert[face[i][1]][1] - vert[face[i][0]][1])*
    (vert[face[i][2]][2] - vert[face[i][1]][2]) -
    (vert[face[i][2]][1] - vert[face[i][1]][1])*
    (vert[face[i][1]][2] - vert[face[i][0]][2]);
   Norm[i][1] = -(vert[face[i][1]][0] - vert[face[i][0]][0])*
    (vert[face[i][2]][2] - vert[face[i][1]][2]) +
    (vert[face[i][2]][0] - vert[face[i][1]][0])*
    (vert[face[i][1]][2] - vert[face[i][0]][2]);
   Norm[i][2] = (vert[face[i][1]][0] - vert[face[i][0]][0])*
    (vert[face[i][2]][1] - vert[face[i][1]][1]) -
    (vert[face[i][2]][0] - vert[face[i][1]][0])*
    (vert[face[i][1]][1] - vert[face[i][0]][1]);
			double mod = Math.sqrt(Norm[i][0]*Norm[i][0] + Norm[i][1]*Norm[i][1] +Norm[i][2]*Norm[i][2]) ;//divided by 255 for color
   			Norm[i][0] /= mod;    Norm[i][1] /= mod;    Norm[i][2] /= mod;
  }
  	
  		/////Zsort
  	for (int i = 0; i < nVert; i++) {
  		Zsort[i] = i;System.out.println(Zsort[i]+" initially");
  	}
  	for (int i = 0; i < nVert; i++) {
  		System.out.println(vert[Zsort[i]][2]+" initially");
  	}
  	for (int i = nVert - 1; --i >= 0;) {
		System.out.println( i +" i");
	    for (int j = 0; j <= i; j++) {
	    	System.out.println( j +" j");
	      int a = Zsort[j],  b = Zsort[j + 1];
	      System.out.println( Zsort[j + 1] +" Zsort[j + 1]");System.out.println(Zsort[j] +" Zsort[j]");
	  	
	     System.out.println( a +" a");System.out.println(b +" b"); 
	     System.out.println(  vert[a][2]+"vert[a][2]"+vert[b][2]+"vert[b][2]");
	      if (vert[a][2] > vert[b][2]) {
	        Zsort[j + 1] = a;   Zsort[j] = b;
	  	   System.out.println( Zsort[j + 1] +" Zsort[j + 1]");System.out.println(Zsort[j] +" Zsort[j]");
	  	   System.out.println(" &&&&&&&&&&&&&&"); 
      
	        }
	    }
	 }
	     
	    for (int i = 0; i < nVert; i++) {
  		System.out.println(Zsort[i]+" finally");}
  		for (int i = 0; i < nVert; i++) {
  		System.out.println(vert[Zsort[i]][2]+" i");}
  		
  		///v.n
  	for(int i=0;i<nFace;i++){
				NoV[i]=Norm[i][0]*0+Norm[i][1]*0+Norm[i][2]*1;
		}
		///l.n	
	for(int i=0;i<nFace;i++){
		NoL[i]=Norm[i][0]*lightX+Norm[i][1]*lightY+Norm[i][2]*lightZ;	
			}
		////h.n
		double HX=lightX+0,HY=lightY+0,HZ=lightZ+1;
		double mod = Math.sqrt(HX*HX + HY*HY + HZ*HZ) ;//divided by 255 for color
   		HX /= mod;    HY /= mod;    HZ /= mod;
	for(int i=0;i<nFace;i++){
				NoH[i]=Norm[i][0]*HX+Norm[i][1]*HY+Norm[i][2]*HZ;
		}
		//show the blue light source
		int screenLightX=((int)(centerX+(denorm*(lightX))));
		int screenLightY=((int)(centerY-(denorm*(lightY))));
		big.setColor(new Color(0,0,255));
		big.drawLine(screenLightX,screenLightY,centerX,centerY);
		big.fillOval(screenLightX,screenLightY-5,10,10);
		
		///fractal
	recursion(iter, 0, 0, 1, 1-1/dim);
  	g2.drawImage(bi, 0, 0,this);  	
	}
			
public void recursion(int n, double Xo, double Yo, double sc, double tr) {
   if(n!=0){
   for (int i = 0; i < nVert; i++){    
       	int is = Zsort[i];    
    	recursion(n-1, Xo + vert[is][0]*tr, Yo + vert[is][1]*tr, sc/dim, tr/dim);
    	}
    }
  if (n==0) {
  	double[][] temp=new double[nVert][3];
  	for(int i=0;i<nVert;i++){
  		temp[i][0]=Xo+vert[i][0]*sc;
		temp[i][1]=Yo+vert[i][1]*sc;
		temp[i][2]=vert[i][2];	
	}
  	drawPolygon(temp);
  }
  }                         

public void drawPolygon(double[][] temp) {
  
    	int[] xPol=new int[4];
		int[] yPol=new int[4];
		
			for(int i=0;i<nVert;i++){ 
			//change to perspective
			
			double u=(1-temp[i][2])/(-2-temp[i][2]);
	  		temp[i][0]=temp[i][0]*(1-u);
	  		temp[i][1]=temp[i][1]*(1-u);
	  		//temp[i][2]=temp[i][2]*(1-u)+(2*u);
			//change to projection and viewing coordinates
			screenVert[i][0]=(int)(centerX+(denorm*(temp[i][0])));
			screenVert[i][1]=(int)(centerY-(denorm*(temp[i][1])));
			//////////
			}	
		
		//draw the polygon.
		for(int i=0;i<nFace ;i++){
			if(NoV[i] > 0.0){ 	 //outward normal co-ordinate system page no.309 Hearn & Baker
				for(int j=0;j<face[i].length;j++){
				int x=face[i][j];
				xPol[j]=screenVert[x][0];
				yPol[j]=screenVert[x][1];
			}
			double  intensity=(NoL[i]+0.02); 
			if(intensity<0) intensity=0;
   			if(intensity>1) intensity=1; 
   			big.setColor(new Color((int)(intensity*R),(int)(intensity*G),(int)(intensity*B)));
			if(wireframe==true) big.drawPolygon(xPol,yPol,face[i].length);
			else big.fillPolygon(xPol,yPol,face[i].length);
		}
		}	
	}
				
}	


	
	
	
	