/*     */ package test;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Insets;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class simple
/*     */   extends JFrame {
/*  11 */   public mypanel pane = new mypanel();
/*  12 */   int width = 60;
/*  13 */   int height = 60;
/*  14 */   int x = 180;
/*  15 */   int y = 0;
/*     */   int insetwidth;
/*     */   int insetheight;
/*  18 */   int[][] locat = new int[13][9];
/*     */   recgles re;
/*     */   
/*     */   public simple(String name) throws InterruptedException {
/*  22 */     super(name);
/*  23 */     setDefaultCloseOperation(3);
/*  24 */     setVisible(true);
/*     */     
/*  26 */     this.re = new recgles();
/*  27 */     this.re.re1.x = 4;
/*  28 */     this.re.re1.y = -1;
/*  29 */     this.re.re2.x = 5;
/*  30 */     this.re.re2.y = -1;
/*  31 */     this.re.re3.x = 6;
/*  32 */     this.re.re3.y = -1;
/*  33 */     this.re.re4.x = 4;
/*  34 */     this.re.re4.y = -2;
/*     */ 
/*     */     
/*  37 */     Insets insert = getInsets();
/*     */     
/*  39 */     System.out.println(insert.top);
/*  40 */     System.out.println(insert.bottom);
/*  41 */     setSize(540 + insert.right + insert.left, 920);
/*     */ 
/*     */ 
/*     */     
/*  45 */     setContentPane(this.pane);
/*     */     
/*  47 */     setBackground(Color.WHITE);
/*  48 */     setLocation(500, 0);
/*     */     
/*  50 */     paint(this.re);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class mypanel
/*     */     extends JPanel
/*     */   {
/*     */     protected void paintComponent(Graphics g) {
/*  67 */       for (int i = 0; i < 13; i++) {
/*     */         
/*  69 */         for (int j = 0; j < 9; j++) {
/*     */           
/*  71 */           if (simple.this.locat[i][j] == 0)
/*     */           {
/*  73 */             g.clearRect(j * 60, i * 60, simple.this.width, simple.this.height);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/*  78 */       for (int x = 60; x <= 540; x += 60)
/*     */       {
/*  80 */         g.drawLine(x, 0, x, 780);
/*     */       }
/*  82 */       for (int y = 60; y <= 780; y += 60)
/*     */       {
/*  84 */         g.drawLine(0, y, 540, y);
/*     */       }
/*     */       
/*  87 */       for (int i = 0; i < 13; i++) {
/*     */         
/*  89 */         for (int j = 0; j < 9; j++) {
/*     */           
/*  91 */           if (simple.this.locat[i][j] > 0)
/*     */           {
/*  93 */             g.fillRect(j * 60, i * 60, simple.this.width, simple.this.height);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public class recgles
/*     */   {
/* 102 */     public recgle re1 = new recgle();
/* 103 */     public recgle re2 = new recgle();
/* 104 */     public recgle re3 = new recgle();
/* 105 */     public recgle re4 = new recgle();
/*     */     
/*     */     public class recgle {
/*     */       public int x;
/*     */       public int y; }
/*     */   }
/*     */   
/*     */   public class recgle {
/*     */     public int x;
/*     */     public int y;
/*     */     
/*     */     public recgle() {}
/*     */   }
/*     */   
/*     */   public void paint(recgles re) {
/* 120 */     int i = 0;
/*     */ 
/*     */ 
/*     */     
/* 124 */     while (re.re1.y + i + 1 != 13 && re.re2.y + i + 1 != 13 && re.re3.y + i + 1 != 13 && re.re4.y + i + 1 != 13) {
/*     */ 
/*     */       
/* 127 */       if (re.re1.y + i + 1 >= 0)
/*     */       {
/* 129 */         if (this.locat[re.re1.y + i + 1][re.re1.x] == 1)
/*     */           break; 
/*     */       }
/* 132 */       if (re.re2.y + i + 1 >= 0)
/*     */       {
/* 134 */         if (this.locat[re.re2.y + i + 1][re.re2.x] == 1)
/*     */           break; 
/*     */       }
/* 137 */       if (re.re3.y + i + 1 >= 0)
/*     */       {
/* 139 */         if (this.locat[re.re3.y + i + 1][re.re3.x] == 1)
/*     */           break; 
/*     */       }
/* 142 */       if (re.re4.y + i + 1 >= 0)
/*     */       {
/* 144 */         if (this.locat[re.re4.y + i + 1][re.re4.x] == 1) {
/*     */           break;
/*     */         }
/*     */       }
/*     */ 
/*     */       
/* 150 */       if (re.re1.y + i >= 0)
/* 151 */         this.locat[re.re1.y + i][re.re1.x] = 0; 
/* 152 */       if (re.re2.y + i >= 0)
/* 153 */         this.locat[re.re2.y + i][re.re2.x] = 0; 
/* 154 */       if (re.re3.y + i >= 0)
/* 155 */         this.locat[re.re3.y + i][re.re3.x] = 0; 
/* 156 */       if (re.re4.y + i >= 0) {
/* 157 */         this.locat[re.re4.y + i][re.re4.x] = 0;
/*     */       }
/*     */       
/* 160 */       i++;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 165 */       if (re.re1.y + i >= 0)
/* 166 */         this.locat[re.re1.y + i][re.re1.x] = 1; 
/* 167 */       if (re.re2.y + i >= 0)
/* 168 */         this.locat[re.re2.y + i][re.re2.x] = 1; 
/* 169 */       if (re.re3.y + i >= 0)
/* 170 */         this.locat[re.re3.y + i][re.re3.x] = 1; 
/* 171 */       if (re.re4.y + i >= 0) {
/* 172 */         this.locat[re.re4.y + i][re.re4.x] = 1;
/*     */       }
/*     */ 
/*     */       
/* 176 */       this.pane.repaint();
/*     */       try {
/* 178 */         Thread.sleep(500L);
/* 179 */       } catch (InterruptedException e) {
/* 180 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pain() {
/* 190 */     for (; this.y < 840; this.y += 60) {
/*     */       
/*     */       try {
/* 193 */         Thread.sleep(100L);
/* 194 */       } catch (InterruptedException e) {
/* 195 */         e.printStackTrace();
/*     */       } 
/* 197 */       System.out.println("重绘");
/* 198 */       this.pane.repaint();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\xianzhikun\Desktop\mytemple\GluttonousSnake\GluttonousSnake.jar!\test\simple.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */