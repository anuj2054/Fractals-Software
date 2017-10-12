import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.border.*;
//////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////
public class Sponge implements ActionListener,Runnable,KeyListener,MouseListener,MouseMotionListener{
	
	 //*to make cube default
	String type="CUBE";
	public double thetaX=0.0,thetaY=0.0, thetaZ=0.0;
	public double translateX=0.0,translateY=0.0,translateZ=0.0;
	public double lightX=1,lightY=0,lightZ=0;
	public int denorm=50;
 	public int nVert=8, nFace=6, iter=1;
 	public int R=0,G=255,B=255;
 	public boolean wireframe=false;
 	public double dim=3;
	public int[][] face = {
		{0,3,2,1},
		{0,1,5,4},
		{4,5,6,7},
		{7,6,2,3},
		{5,1,2,6},
		{0,4,7,3},
		};
    public double[][] vert = {
    	{0,0,0},
    	{1,0,0},
    	{1,1,0},
    	{0,1,0},
    	{0,0,1},
    	{1,0,1},
    	{1,1,1},
    	{0,1,1}
    	};
  private volatile Thread rotateXThread = null;
  private volatile Thread rotateYThread = null;
  private volatile Thread rotateZThread = null;
  boolean stop=new Boolean(true);
	FlowLayout layout2= new FlowLayout(FlowLayout.LEFT);			
	JPanel panelM= new JPanel();
	JPanel panelB= new JPanel();
	JPanel panelC = new JPanel();
	JMenuBar SpongeMenuBar= new JMenuBar();
	JFrame frameM= new JFrame("Sponge 3D");
    BorderLayout layout1= new BorderLayout();
	JLabel labelMouseX,labelMouseY;
	FComponent fractal1=new FComponent(nFace,nVert,vert,face,denorm,lightX,lightY,lightZ,iter,dim,R,B,G,wireframe);	
	int mx0=0,my0=0,mx1=0,my1=0; 
	double fiY=0.0,fiX=0.0,dfi = .001;
		
public static void main(String[] args) {
    	 System.out.println("entering main");
    	 Sponge sponge1= new Sponge();
    }	
			
public Sponge(){
	  	System.out.println("entering Sponge constructor");
    	frameM.setLayout(layout1);
    	frameM.setJMenuBar(SpongeMenuBar);
		frameM.add(panelB,BorderLayout.EAST);
		frameM.add(panelC,BorderLayout.SOUTH);	
		frameM.add(panelM,BorderLayout.CENTER);	
		frameM.add(panelM);
		frameM.setSize(600,550);
		frameM.setVisible(true);	
		panelB.setBorder(BorderFactory.createLineBorder(Color.black));
		panelC.setBorder(BorderFactory.createLineBorder(Color.black));					
////////////////////////////////////////////////////////////
		System.out.println("entering panelC constructor");
		panelC.setLayout(layout2);	
		labelMouseX=new JLabel(" ");
		labelMouseY=new JLabel(" ");
		panelC.add(labelMouseX);
		panelC.add(labelMouseY);	

	JMenu menu,submenu,subsubmenu;
	JMenuItem item;
	    System.out.println("entering spongebarmenu constructor");	
         
        menu = new JMenu("Type");
        menu.setMnemonic(KeyEvent.VK_T);
        SpongeMenuBar.add(menu);
        
        item = new JMenuItem("tetra");
        item.addActionListener(this);
        item.setActionCommand("tetra");
        menu.add(item);
        item = new JMenuItem("hexa");
        item.addActionListener(this);
        item.setActionCommand("hexa");
        menu.add(item);
        item = new JMenuItem("penta");
        item.addActionListener(this);
        item.setActionCommand("penta");
        menu.add(item);
        item = new JMenuItem("octa");
        item.addActionListener(this);
        item.setActionCommand("octa");
        menu.add(item);
        item = new JMenuItem("deca");
        item.addActionListener(this);
        item.setActionCommand("deca");
        menu.add(item);
        item = new JMenuItem("cube");
        item.addActionListener(this);
        item.setActionCommand("cube");
        menu.add(item);
        
        
        menu= new JMenu("Transforms");
        menu.setMnemonic(KeyEvent.VK_R);
        SpongeMenuBar.add(menu);
        
        submenu= new JMenu("rotate");
        item=new JMenuItem("rotateX");
        item.addActionListener(this);
        item.setActionCommand("rotateX");
        submenu.add(item);
         item= new JMenuItem("rotateY");
        item.addActionListener(this);
        item.setActionCommand("rotateY");
        submenu.add(item);
         item= new JMenuItem("rotateZ");
        item.addActionListener(this);
        item.setActionCommand("rotateZ");
        submenu.add(item);
         item=new JMenuItem("Stop");
        item.addActionListener(this);
        item.setActionCommand("rotateStop");
        submenu.add(item);
        menu.add(submenu);
        
        submenu=new JMenu("translate");
        menu.add(submenu);
         
        subsubmenu=new JMenu("X");
        submenu.add(subsubmenu);
 
        item= new JMenuItem("+");
        item.addActionListener(this);
        item.setActionCommand("translateX+");
        subsubmenu.add(item);
        item= new JMenuItem("--");
        item.addActionListener(this);
        item.setActionCommand("translateX-");
        subsubmenu.add(item);
       
        subsubmenu=new JMenu("Y");
        submenu.add(subsubmenu);
 
        item= new JMenuItem("+");
        item.addActionListener(this);
        item.setActionCommand("translateY+");
        subsubmenu.add(item);
        item= new JMenuItem("--");
        item.addActionListener(this);
        item.setActionCommand("translateY-");
        subsubmenu.add(item);
       
       subsubmenu=new JMenu("Z");
        submenu.add(subsubmenu);
 
        item= new JMenuItem("+");
        item.addActionListener(this);
        item.setActionCommand("translateZ+");
        subsubmenu.add(item);
        item= new JMenuItem("--");
        item.addActionListener(this);
        item.setActionCommand("translateZ-");
        subsubmenu.add(item);
        
		menu = new JMenu("Controls");
		menu.setMnemonic(KeyEvent.VK_C);
		SpongeMenuBar.add(menu);
		
		submenu= new JMenu("wireframe");
        menu.add(submenu);
        item=new JMenuItem("yes");
        item.addActionListener(this);
        item.setActionCommand("yes");
        submenu.add(item);
        item=new JMenuItem("no");
        item.addActionListener(this);
        item.setActionCommand("no");
        submenu.add(item);
        
		submenu= new JMenu("color");
        menu.add(submenu);
        item=new JMenuItem("red");
        item.addActionListener(this);
        item.setActionCommand("colorRed");
        submenu.add(item);
        item=new JMenuItem("green");
        item.addActionListener(this);
        item.setActionCommand("colorGreen");
        submenu.add(item);
        item=new JMenuItem("blue");
        item.addActionListener(this);
        item.setActionCommand("colorBlue");
        submenu.add(item);
        item=new JMenuItem("magenta");
        item.addActionListener(this);
        item.setActionCommand("colorMagenta");
        submenu.add(item);
        item=new JMenuItem("crimson");
        item.addActionListener(this);
        item.setActionCommand("colorCrimson");
        submenu.add(item);
        item=new JMenuItem("yellow");
        item.addActionListener(this);
        item.setActionCommand("colorYellow");
        submenu.add(item);
        item=new JMenuItem("black");
        item.addActionListener(this);
        item.setActionCommand("colorBlack");
        submenu.add(item);
        
		submenu = new JMenu("PointLightSource");
		item=new JMenuItem("light1");
        item.addActionListener(this);
        item.setActionCommand("light1");
        submenu.add(item);
		item=new JMenuItem("light2");
        item.addActionListener(this);
        item.setActionCommand("light2");
        submenu.add(item);
		item=new JMenuItem("light3");
        item.addActionListener(this);
        item.setActionCommand("light3");
        submenu.add(item);
		item=new JMenuItem("light4");
        item.addActionListener(this);
        item.setActionCommand("light4");
        submenu.add(item);
		
		
		menu.add(submenu);
		
	
        submenu= new JMenu("1/dimension");
        menu.add(submenu);
        item=new JMenuItem("1");
        item.addActionListener(this);
        item.setActionCommand("dim1");
        submenu.add(item);
        item=new JMenuItem("2");
        item.addActionListener(this);
        item.setActionCommand("dim2");
        submenu.add(item);
        item=new JMenuItem("3");
        item.addActionListener(this);
        item.setActionCommand("dim3");
        submenu.add(item);
        item=new JMenuItem("4");
        item.addActionListener(this);
        item.setActionCommand("dim4");
        submenu.add(item);
        item=new JMenuItem("5");
        item.addActionListener(this);
        item.setActionCommand("dim5");
        submenu.add(item);
        
        
        submenu= new JMenu("iteration");
        menu.add(submenu);
        item=new JMenuItem("0");
        item.addActionListener(this);
        item.setActionCommand("iter0");
        submenu.add(item);
        item=new JMenuItem("1");
        item.addActionListener(this);
        item.setActionCommand("iter1");
        submenu.add(item);
        item=new JMenuItem("2");
        item.addActionListener(this);
        item.setActionCommand("iter2");
        submenu.add(item);
        item=new JMenuItem("3");
        item.addActionListener(this);
        item.setActionCommand("iter3");
        submenu.add(item);
        item=new JMenuItem("4");
        item.addActionListener(this);
        item.setActionCommand("iter4");
        submenu.add(item);
        
        menu=new JMenu("About");
        menu.setMnemonic(KeyEvent.VK_A);
        SpongeMenuBar.add(menu);
        
        item=new JMenuItem("about Fractals");
        item.addActionListener(this);
        item.setActionCommand("aboutFractals");
        menu.add(item);
        item= new JMenuItem("about The project");
        item.addActionListener(this);
        item.setActionCommand("aboutProject");
        menu.add(item);
		item= new JMenuItem("about 3DGraphics");
		item.addActionListener(this);
		item.setActionCommand("aboutGraphics");
		menu.add(item);
	
/////////////////////////////////////////////////////////////////
	JLabel label1;
	JLabel label2;
	JLabel label3;

		System.out.println("entering panelB constructor");
		BoxLayout layout3= new BoxLayout(panelB,BoxLayout.Y_AXIS);
		panelB.setLayout(layout3);
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		TitledBorder titled;
		label1= new JLabel();
		label2= new JLabel();
		label3= new JLabel();
		
		label1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		titled=BorderFactory.createTitledBorder(loweredetched,"STATUS",TitledBorder.LEFT,TitledBorder.TOP);
		label1.setBorder(titled);
		
		label2.setAlignmentX(Component.RIGHT_ALIGNMENT);
		titled=BorderFactory.createTitledBorder(loweredetched,"normalized VERTEX TABLE",TitledBorder.LEFT,TitledBorder.TOP);
		label2.setBorder(titled);
		
		label3.setAlignmentX(Component.RIGHT_ALIGNMENT);
		titled=BorderFactory.createTitledBorder(loweredetched,"FACE TABLE",TitledBorder.LEFT,TitledBorder.TOP);
		label3.setBorder(titled);
	
		label1.setText("<html><table><tr>");
		label1.setText(label1.getText()+"<td><b>Sponge fractal type </b></td><td align=RIGHT border=1><font color=red>"+type+"</font></td>");
	    label1.setText(label1.getText()+"</tr><tr>");
	    label1.setText(label1.getText()+"<td><b>Num of Vertices </b></td><td align=RIGHT>"+nVert+"</td>");
	    label1.setText(label1.getText()+"</tr><tr>");
	    label1.setText(label1.getText()+"<td><b>Num of Faces </b></td><td align=RIGHT>"+nFace+"</td>");
	    label1.setText(label1.getText()+"</tr><tr>");
	    label1.setText(label1.getText()+"<td><b>Iteration </b></td><td align=RIGHT>"+iter+"</td>");
	    label1.setText(label1.getText()+"</tr><tr>");
	    label1.setText(label1.getText()+"<td><b>1/dimension </b></td><td align=RIGHT>"+ dim  +"</td>");
	    label1.setText(label1.getText()+"</tr></table></html>");
	    panelB.add(label1); 
		///////////////////////
		label2.setText("<html><table><tr>");
		for (int i = 0; i < vert.length; i++) {
		  for (int j = 0; j < vert[i].length; j++) {
	        label2.setText(label2.getText()+"<td>"+(int)vert[i][j]+"</td>");
	     }
	     label2.setText(label2.getText()+"</tr><tr>");
	   }
	   label2.setText(label2.getText()+"</tr></table></html>");
	   panelB.add(label2); 
	   ////////////////////////////
	   	label3.setText("<html><table><tr>");
		for (int i = 0; i < face.length; i++) {
		  for (int j = 0; j < face[i].length; j++) {
	        label3.setText(label3.getText()+"<td>"+face[i][j]+"</td>");
	     }
	     label3.setText(label3.getText()+"</tr><tr>");
	   }
	   label3.setText(label3.getText()+"</tr></table></html>");
	   panelB.add(label3); 
 		panelM.addKeyListener(this);
		panelM.addMouseListener(this);
		panelM.addMouseMotionListener(this);	
		panelM.add(fractal1);
}
	
///////////////////////////////////////////////////////////////////
public void keyPressed (KeyEvent ke){
			double[][] temp;
			temp= new double[nVert][3];
			System.out.println(" "+ke.getKeyCode()+" ");
				System.out.println("entering key");
		
		if("x".equals(ke.getKeyText(ke.getKeyCode()))) {
			thetaX+=0.001;
			for(int i=0;i<nVert;i++){	
			temp[i][0]=vert[i][0];
			temp[i][1]=vert[i][1]*Math.cos(thetaX)-vert[i][2]*Math.sin(thetaX);
			temp[i][2]=vert[i][1]*Math.sin(thetaX)+vert[i][2]*Math.cos(thetaX);
			vert[i][0]=temp[i][0];
			vert[i][1]=temp[i][1];
			vert[i][2]=temp[i][2];
			}	
			}
			
		if(ke.getKeyChar()=='y') {
			thetaY+=0.001;
			for(int i=0;i<nVert;i++){		
			temp[i][0]=vert[i][0]*Math.cos(thetaY)+vert[i][2]*Math.sin(thetaY);
			temp[i][1]=vert[i][1];
			temp[i][2]=-vert[i][0]*Math.sin(thetaY)+vert[i][2]*Math.cos(thetaY);
			vert[i][0]=temp[i][0];
			vert[i][1]=temp[i][1];
			vert[i][2]=temp[i][2];
			}	
			}
			
		if(ke.getKeyChar()=='z') {
				thetaZ+=0.001;
			for(int i=0;i<nVert;i++){		
			temp[i][0]=vert[i][0]*Math.cos(thetaZ)-vert[i][1]*Math.sin(thetaZ);
			temp[i][1]=vert[i][0]*Math.sin(thetaZ)+vert[i][1]*Math.cos(thetaZ);
			temp[i][2]=vert[i][2];
			vert[i][0]=temp[i][0];
			vert[i][1]=temp[i][1];
			vert[i][2]=temp[i][2];	
			}
			}
			
		if(ke.getKeyChar()=='a') {
			translateX-=0.01;
			for(int i=0;i<nVert;i++){
				System.out.println(" sadas"+translateX+" ");		
			temp[i][0]=vert[i][0]+translateX;
			temp[i][1]=vert[i][1];
			temp[i][2]=vert[i][2];
			vert[i][0]=temp[i][0];
			vert[i][1]=temp[i][1];
			vert[i][2]=temp[i][2];	
			}
			}
			
		if(ke.getKeyChar()=='s') {
			System.out.println(" asda"+translateY+" ");
			translateY-=0.01;
			for(int i=0;i<nVert;i++){		
			temp[i][0]=vert[i][0];
			temp[i][1]=vert[i][1]+translateY;
			temp[i][2]=vert[i][2];
			vert[i][0]=temp[i][0];
			vert[i][1]=temp[i][1];
			vert[i][2]=temp[i][2];	
			}
			}
			
		if(ke.getKeyChar()=='d') {
			System.out.println("d pressed");
			System.out.println(" a12asda"+translateX+" ");
			
			translateX+=0.01;
			for(int i=0;i<nVert;i++){		
			temp[i][0]=vert[i][0]+translateX;
			temp[i][1]=vert[i][1];
			temp[i][2]=vert[i][2];
			vert[i][0]=temp[i][0];
			vert[i][1]=temp[i][1];
			vert[i][2]=temp[i][2];	
			}
			}
			
		if(ke.getKeyChar()=='w') {
			translateY+=0.01;
			for(int i=0;i<nVert;i++){		
			temp[i][0]=vert[i][0];
			temp[i][1]=vert[i][1]+translateY;
			temp[i][2]=vert[i][2];
			vert[i][0]=temp[i][0];
			vert[i][1]=temp[i][1];
			vert[i][2]=temp[i][2];	
			}
			}
		
		if(ke.getKeyChar()=='+') {
			translateZ+=0.01;
			for(int i=0;i<nVert;i++){		
			temp[i][0]=vert[i][0];
			temp[i][1]=vert[i][1];
			temp[i][2]=vert[i][2]+translateZ;
			vert[i][0]=temp[i][0];
			vert[i][1]=temp[i][1];
			vert[i][2]=temp[i][2];	
			}
			}
			
		if(ke.getKeyChar()=='-') {
			translateZ-=0.01;
			for(int i=0;i<nVert;i++){		
			temp[i][0]=vert[i][0];
			temp[i][1]=vert[i][1];
			temp[i][2]=vert[i][2]+translateZ;
			vert[i][0]=temp[i][0];
			vert[i][1]=temp[i][1];
			vert[i][2]=temp[i][2];	
			}
			}
			panelM.repaint();
				
	}
public void keyReleased (KeyEvent ke){};
public void keyTyped (KeyEvent ke){};
public void mouseClicked(MouseEvent me){}       
public void mouseReleased(MouseEvent me){}
public void mouseEntered(MouseEvent me) {}
public void mouseExited(MouseEvent me)  {}
public void mouseMoved(MouseEvent me) {
		int movX=0,movY=0;
		movX=me.getX();
		movY=me.getY();
		labelMouseX.setText("X position "+movX);
		labelMouseY.setText("Y position "+movY);
		me.consume();	
		}	
	
public void mousePressed(MouseEvent me) {
	   System.out.println("mouse pressed");
	    if ( me.isControlDown() ) {
	     System.out.println("with ctrl");
	    iter--;  if (iter < 0) iter = 0;
	    }
	  if ( me.isAltDown() ){
	     System.out.println("with alt");
	    iter++;
	    }
	    
	    
	    //repaint();panelB.paint();
	 
	}
public void mouseDragged(MouseEvent me) {
	  System.out.println("mouse dragged----rotating");
	  mx1 = me.getX();   my1 = me.getY();
	   fiY += dfi*(mx1 - mx0);   mx0 = mx1;
	   fiX += dfi*(my1 - my0);   my0 = my1;
	  //rotate();painted=false;
	  double cx = Math.cos(fiX), cy = Math.cos(fiY),
	         sx = Math.sin(fiX), sy = Math.sin(fiY),
	         m00 =  cy,    m02 =  sy,
             m10 = sy*sx, m11 =  cx, m12 = -sx*cy,
	        m20 = -cx*sy, m21 = sx, m22 = cx*cy;//for norm only
	  //here rotation matrix multiplication with vert matrix is done
	  double[][] temp;
	 temp= new double[nVert][3];
	  for (int i = 0; i < nVert; i++){
	   temp[i][0] = m00*vert[i][0] + m02*vert[i][2];
	   temp[i][1] = m10*vert[i][0] + m11*vert[i][1] + m12*vert[i][2];
	   temp[i][2] = m20*vert[i][0] + m21*vert[i][1] + m22*vert[i][2];//for Zsort
	   vert[i][0]=temp[i][0];
	   vert[i][1]=temp[i][1];
	   vert[i][2]=temp[i][2];
	   }
	  panelM.add(fractal1);
	
	}

public void run() {
	     Thread myThread = Thread.currentThread();
	     	System.out.println(stop+" stop");
	     	while ((rotateXThread == myThread)&(stop==false)) {
	            thetaX+=0.001;
	            double[][] temp;
				temp= new double[nVert][3];
				for(int i=0;i<nVert;i++){	
				temp[i][0]=vert[i][0];
				temp[i][1]=vert[i][1]*Math.cos(thetaX)-vert[i][2]*Math.sin(thetaX);
				temp[i][2]=vert[i][1]*Math.sin(thetaX)+vert[i][2]*Math.cos(thetaX);
				vert[i][0]=temp[i][0];
				vert[i][1]=temp[i][1];
				vert[i][2]=temp[i][2];
				}
			 panelM.add(fractal1);
	            try {
	                Thread.sleep(150);
	            } catch (InterruptedException e){ }
			}
			while ((rotateYThread == myThread)&(stop==false)) {
	            thetaY+=0.001;
	            double[][] temp;
				temp= new double[nVert][3];
				for(int i=0;i<nVert;i++){	
				temp[i][0]=vert[i][0]*Math.cos(thetaY)+vert[i][2]*Math.sin(thetaY);
				temp[i][1]=vert[i][1];
				temp[i][2]=-vert[i][0]*Math.sin(thetaY)+vert[i][2]*Math.cos(thetaY);
				vert[i][0]=temp[i][0];
				vert[i][1]=temp[i][1];
				vert[i][2]=temp[i][2];
				}
				 panelM.add(fractal1);
	            try {
	                Thread.sleep(150);
	            } catch (InterruptedException e){ }
			}
			while ((rotateZThread == myThread)&(stop==false)) {
	            thetaZ+=0.001;
	            double[][] temp;
				temp= new double[nVert][3];
				for(int i=0;i<nVert;i++){	
				temp[i][0]=vert[i][0]*Math.cos(thetaZ)-vert[i][1]*Math.sin(thetaZ);
				temp[i][1]=vert[i][0]*Math.sin(thetaZ)+vert[i][1]*Math.cos(thetaZ);
				temp[i][2]=vert[i][2];
				vert[i][0]=temp[i][0];
				vert[i][1]=temp[i][1];
				vert[i][2]=temp[i][2];
				}
			 panelM.add(fractal1);
	            try {
	                Thread.sleep(150);
	            } catch (InterruptedException e){ }
			}   
		}  
	   	    
public void actionPerformed(ActionEvent ae){
	
		if("yes".equals(ae.getActionCommand())){	
		wireframe= true; 
	    }
	    if("no".equals(ae.getActionCommand())){	
		wireframe= false; 
	    }
	    
		if("aboutFractals".equals(ae.getActionCommand())){	 
	    }
		if("aboutProject".equals(ae.getActionCommand())){
			}
		if("aboutGraphics".equals(ae.getActionCommand())){
			}
		
		if("colorBlack".equals(ae.getActionCommand())){
			R=0;G=0;B=0;
			}	
		if("colorRed".equals(ae.getActionCommand())){
			R=255;G=0;B=0;
			}
		if("colorGreen".equals(ae.getActionCommand())){
			R=0;G=255;B=0;
			}
		if("colorMagenta".equals(ae.getActionCommand())){
			R=0;G=255;B=255;
			}
		if("colorBlue".equals(ae.getActionCommand())){
			R=0;G=0;B=255;
			}
		if("colorCrimson".equals(ae.getActionCommand())){
			R=255;G=0;B=255;
			}
		if("colorYellow".equals(ae.getActionCommand())){
			R=255;G=255;B=0;
			}
		
		if("iter0".equals(ae.getActionCommand())){
			iter=0;
			}
		if("iter1".equals(ae.getActionCommand())){
			iter=1;
			}
		if("iter2".equals(ae.getActionCommand())){
			iter=2;
			}
		if("iter3".equals(ae.getActionCommand())){
			iter=3;
			}
		if("iter4".equals(ae.getActionCommand())){
			iter=4;
			}
		if("dim1".equals(ae.getActionCommand())){
			dim=1;
			}
		if("dim2".equals(ae.getActionCommand())){
			dim=2;
			}
		if("dim3".equals(ae.getActionCommand())){
			dim=3;
			}
		if("dim4".equals(ae.getActionCommand())){
			dim=4;
			}
		if("dim5".equals(ae.getActionCommand())){
			dim=5;
			}	
		if("light1".equals(ae.getActionCommand())){
			lightX=1;lightY=0;lightZ=0;
			}
		if("light2".equals(ae.getActionCommand())){
			lightX=0;lightY=1;lightZ=0;
			}
		if("light3".equals(ae.getActionCommand())){
			lightX=0;lightY=0;lightZ=1;
			}
		if("light4".equals(ae.getActionCommand())){
			lightX=1;lightY=1;lightZ=1;
			}
		if("rotateStop".equals(ae.getActionCommand())) {
				 stop=true;System.out.println(stop+"stop");
				}
	
		if("rotateX".equals(ae.getActionCommand())) {
				 stop=false;System.out.println(stop+"stop");
				rotateXThread = new Thread(this, "Clock");
	         	rotateXThread.start();
	      	
				}
		
		if("rotateY".equals(ae.getActionCommand())) {
				 stop=false;System.out.println(stop+"stop");
				rotateYThread = new Thread(this, "Clock");
         		rotateYThread.start();
      			
			}
			
		if("rotateZ".equals(ae.getActionCommand())) {
				stop=false;System.out.println(stop+"stop");
         		rotateZThread = new Thread(this, "Clock");
         		rotateZThread.start();
      		
			}
			
		if("translateX-".equals(ae.getActionCommand())) {
			translateX-=1;
			double[][] temp;
			temp= new double[nVert][3];
			for(int i=0;i<nVert;i++){
			System.out.println(" sadas"+translateX+" ");		
			temp[i][0]=vert[i][0]+translateX;
			temp[i][1]=vert[i][1];
			temp[i][2]=vert[i][2];
			vert[i][0]=temp[i][0];
			vert[i][1]=temp[i][1];
			vert[i][2]=temp[i][2];	
			}
			
			}
			
		if("translateY-".equals(ae.getActionCommand())) {
			System.out.println(" asda"+translateY+" ");
			translateY-=1;
			double[][] temp;
			temp= new double[nVert][3];
			for(int i=0;i<nVert;i++){		
			temp[i][0]=vert[i][0];
			temp[i][1]=vert[i][1]+translateY;
			temp[i][2]=vert[i][2];
			vert[i][0]=temp[i][0];
			vert[i][1]=temp[i][1];
			vert[i][2]=temp[i][2];	
			}
			
			}
			
		if("translateX+".equals(ae.getActionCommand())) {
			System.out.println("d pressed");
			System.out.println(" a12asda"+translateX+" ");
			
			translateX+=1;
			double[][] temp;
			temp= new double[nVert][3];
			for(int i=0;i<nVert;i++){		
			temp[i][0]=vert[i][0]+translateX;
			temp[i][1]=vert[i][1];
			temp[i][2]=vert[i][2];
			vert[i][0]=temp[i][0];
			vert[i][1]=temp[i][1];
			vert[i][2]=temp[i][2];	
			}
			
			}
			
		if("translateY+".equals(ae.getActionCommand())) {
			translateY+=1;
			double[][] temp;
			temp= new double[nVert][3];
			for(int i=0;i<nVert;i++){		
			temp[i][0]=vert[i][0];
			temp[i][1]=vert[i][1]+translateY;
			temp[i][2]=vert[i][2];
			vert[i][0]=temp[i][0];
			vert[i][1]=temp[i][1];
			vert[i][2]=temp[i][2];	
			}
			
			}
		
		if("translateZ+".equals(ae.getActionCommand())) {
			translateZ+=1;
			double[][] temp;
			temp= new double[nVert][3];
			for(int i=0;i<nVert;i++){		
			temp[i][0]=vert[i][0];
			temp[i][1]=vert[i][1];
			temp[i][2]=vert[i][2]+translateZ;
			vert[i][0]=temp[i][0];
			vert[i][1]=temp[i][1];
			vert[i][2]=temp[i][2];	
			}
			
			}
			
		if("translateZ-".equals(ae.getActionCommand())) {
			translateZ-=1;
			double[][] temp;
			temp= new double[nVert][3];
			for(int i=0;i<nVert;i++){		
			temp[i][0]=vert[i][0];
			temp[i][1]=vert[i][1];
			temp[i][2]=vert[i][2]+translateZ;
			vert[i][0]=temp[i][0];
			vert[i][1]=temp[i][1];
			vert[i][2]=temp[i][2];	
			}	
			}
		if("tetra".equals(ae.getActionCommand())){		
	 		type="tetra";
	 		//lightX=1;lightY=0;lightZ=0;
	 		nVert=4; nFace=4; iter=1;dim=2;denorm=300;
			face= new int[nFace][3];
			vert= new double[nVert][3];
			 face [0][0]=2;face [0][1]=1;face [0][2]=0;
			 face [1][0]=0;face [1][1]=1;face [1][2]=3;	 
			 face [2][0]=1;face [2][1]=2;face [2][2]=3;
			 face [3][0]=3;face [3][1]=2;face [3][2]=0;
			 vert [0][0]=0.577;vert [0][1]=-0.2;vert [0][2]=0.0;
			 vert [1][0]=-0.29;vert [1][1]=-0.2;vert [1][2]=-0.5;
			 vert [2][0]=-0.29;vert [2][1]=-0.2;vert [2][2]=0.5;
			 vert [3][0]=0.0;  vert [3][1]=0.61;vert [3][2]=0.0;
			}
				
		if("hexa".equals(ae.getActionCommand())){		
	 		type="hexa";	
	 		//lightX=1;lightY=0;lightZ=0;
	 		nVert=5; nFace=6; iter=1;dim=2;denorm=200;
			face= new int[nFace][3];
			vert= new double[nVert][3];
			 face [0][0]=3;face [0][1]=1;face [0][2]=0;
			 face [1][0]=3;face [1][1]=2;face [1][2]=1; 
			 face [2][0]=3;face [2][1]=0;face [2][2]=2;
			 face [3][0]=4;face [3][1]=2;face [3][2]=0;
			 face [4][0]=4;face [4][1]=1;face [4][2]=2;
			 face [5][0]=4;face [5][1]=0;face [5][2]=1;
			
			 vert [0][0]=0.577;vert [0][1]=0;vert [0][2]=0.0;
			 vert [1][0]=-0.29;vert [1][1]=0;vert [1][2]=-0.5;
			 vert [2][0]=-0.29;vert [2][1]=0;vert [2][2]=0.5;
			 vert [3][0]=0.0;vert [3][1]=-0.81;vert [3][2]=0.0;
			 vert [4][0]=0.0;vert [4][1]=0.81;vert [4][2]=0.0;
			}

		if("penta".equals(ae.getActionCommand())){		
	 		type="penta";
	 		//lightX=1;lightY=0;lightZ=0;
	 		nVert=5; nFace=6; iter=1;dim=2;denorm=150;
			face= new int[nFace][3];
			vert= new double[nVert][3];
			 
			 vert [0][0]=1;vert [0][1]=0;vert [0][2]=0;
			 vert [1][0]=0;vert [1][1]=0;vert [1][2]=-1;	 
			 vert [2][0]=-1;vert [2][1]=0;vert [2][2]=0;
			 vert [3][0]=0;vert [3][1]=0;vert [3][2]=1;
			 vert [4][0]=0;vert [4][1]=1;vert [4][2]=0;	
			
			face [0][0]=4;face [0][1]=0;face [0][2]=1;
			 face [1][0]=4;face [1][1]=1;face [1][2]=2;	 
			 face [2][0]=4;face [2][1]=2;face [2][2]=3;
			 face [3][0]=4;face [3][1]=3;face [3][2]=0;
			 face [4][0]=2;face [4][1]=1;face [4][2]=3;
			 face [5][0]=3;face [5][1]=1;face [5][2]=0;
			}
			
		if("octa".equals(ae.getActionCommand())){		
	 		type="octa";
	 		//lightX=1;lightY=0;lightZ=0;
	 		nVert=6; nFace=8; iter=1;dim=2;denorm=150;
			face= new int[nFace][3];
			vert= new double[nVert][3];
			 
			 vert [0][0]=1;vert [0][1]=0;vert [0][2]=0;
			 vert [1][0]=0;vert [1][1]=0;vert [1][2]=-1;	 
			 vert [2][0]=-1;vert [2][1]=0;vert [2][2]=0;
			 vert [3][0]=0;vert [3][1]=0;vert [3][2]=1;
			 vert [4][0]=0;vert [4][1]=1;vert [4][2]=0;	 
			 vert [5][0]=0;vert [5][1]=-1;vert [5][2]=0;
			
			face [0][0]=4;face [0][1]=0;face [0][2]=1;
			 face [1][0]=4;face [1][1]=1;face [1][2]=2;	 
			 face [2][0]=4;face [2][1]=2;face [2][2]=3;
			 face [3][0]=4;face [3][1]=3;face [3][2]=0;
			 face [4][0]=5;face [4][1]=1;face [4][2]=0;
			 face [5][0]=5;face [5][1]=2;face [5][2]=1;	 
			 face [6][0]=5;face [6][1]=3;face [6][2]=2;
			 face [7][0]=5;face [7][1]=0;face [7][2]=3;
			
	 		}
	 		
	 	if("deca".equals(ae.getActionCommand())){	
	 		type="deca";	
	 		//lightX=1;lightY=0;lightZ=0;
	 		nVert=7; nFace=10; iter=1;dim=2;denorm=200;
			face= new int[nFace][3];
			vert= new double[nVert][3];
			 
			 vert [0][0]=1;vert [0][1]=0;vert [0][2]=0;
			 vert [1][0]=0;vert [1][1]=0;vert [1][2]=-1;	 
			 vert [2][0]=-0.5;vert [2][1]=0;vert [2][2]=-0.5;
			 vert [3][0]=-0.5;vert [3][1]=0;vert [3][2]=0.5;
			 vert [4][0]=0;vert [4][1]=0;vert [4][2]=1;	 
			 vert [5][0]=0;vert [5][1]=-1;vert [5][2]=0;
			 vert [6][0]=0;vert [6][1]=1;vert [6][2]=0;
			
			 face [0][0]=5;face [0][1]=0;face [0][2]=1;
			 face [1][0]=5;face [1][1]=1;face [1][2]=2;	 
			 face [2][0]=5;face [2][1]=2;face [2][2]=3;
			 face [3][0]=5;face [3][1]=3;face [3][2]=4;
			 face [4][0]=5;face [4][1]=4;face [4][2]=0;
			 face [5][0]=6;face [5][1]=0;face [5][2]=4;	 
			 face [6][0]=6;face [6][1]=4;face [6][2]=3;
			 face [7][0]=6;face [7][1]=3;face [7][2]=2;
			 face [8][0]=6;face [8][1]=2;face [8][2]=1;
			 face [9][0]=6;face [9][1]=1;face [9][2]=0;
			
	 		}
	 			
	 	if("cube".equals(ae.getActionCommand())){
	 		type="cube";
	 		//lightX=1;lightY=0;lightZ=0;
	 		nVert=8;nFace=6; iter=1;dim=3;denorm=150;
			face= new int[nFace][4];
			vert= new double[nVert][3];
			 vert [0][0]=0;vert [0][1]=0;vert [0][2]=0;
			 vert [1][0]=1;vert [1][1]=0;vert [1][2]=0;	 
			 vert [2][0]=1;vert [2][1]=1;vert [2][2]=0;
			 vert [3][0]=0;vert [3][1]=1;vert [3][2]=0;
			 vert [4][0]=0;vert [4][1]=0;vert [4][2]=1;	 
			 vert [5][0]=1;vert [5][1]=0;vert [5][2]=1;
			 vert [6][0]=1;vert [6][1]=1;vert [6][2]=1;
			 vert [7][0]=0;vert [7][1]=1;vert [7][2]=1;
			 
			 face [0][0]=0;face [0][1]=3;face [0][2]=2;face[0][3]=1;
			 face [1][0]=0;face [1][1]=1;face [1][2]=5;	face[1][3]= 4;
			 face [2][0]=4;face [2][1]=5;face [2][2]=6;face[2][3]=7;
			 face [3][0]=7;face [3][1]=6;face [3][2]=2;face[3][3]=3;
			 face [4][0]=5;face [4][1]=1;face [4][2]=2;face[4][3]=6;
			 face [5][0]=0;face [5][1]=4;face [5][2]=7;	 face[5][3]=3;		
	 		}
	 		
	 		panelM.add(fractal1);
	}	
}
///////////////////////////////////////////////////////////


