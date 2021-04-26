/*     */ package test;
/*     */ 
/*     */ import guiFrame.SimpleFrame;
/*     */ import java.awt.Container;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.Random;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Snake
/*     */   extends SimpleFrame
/*     */ {
/*     */   public mypanel pane;
/*     */   public Container content;
/*  27 */   Random random = new Random();
/*     */   
/*  29 */   int style = 5;
/*     */   
/*     */   public JButton up;
/*     */   
/*     */   public JButton down;
/*     */   
/*     */   public JButton left;
/*     */   
/*     */   public JButton right;
/*     */   
/*     */   public JButton start;
/*     */   
/*     */   public JButton pause;
/*     */   
/*     */   public static final String scorestr = "当前得分：";
/*     */   public static final String scorestr2 = "最终得分：";
/*     */   public static final int easy = 1000;
/*     */   public static final int normal = 700;
/*     */   public static final int hard = 500;
/*     */   public int sleepTime;
/*     */   boolean isRun = false;
/*     */   public JLabel score;
/*     */   public JLabel nandu;
/*     */   public JComboBox<String> check;
/*     */   public MoveThread thread;
/*     */   public MyStack mySnake;
/*  55 */   public int[][] location = new int[13][13];
/*     */ 
/*     */   
/*     */   public Snake(String name) {
/*  59 */     super(name);
/*  60 */     setDefaultCloseOperation(3);
/*  61 */     setVisible(true);
/*  62 */     Insets insert = getInsets();
/*     */     
/*  64 */     System.out.println(insert.top);
/*  65 */     System.out.println(insert.bottom);
/*  66 */     setSize(780 + insert.right + insert.left + 200, 780 + insert.top + insert.bottom);
/*     */     
/*  68 */     this.content = getContentPane();
/*     */     
/*  70 */     setContentPane(this.content);
/*     */   }
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
/*  83 */       g.clearRect(0, 0, 780, 780);
/*     */       
/*  85 */       for (int i = 0; i < 13; i++) {
/*     */         
/*  87 */         for (int j = 0; j < 13; j++) {
/*     */           
/*  89 */           if (Snake.this.location[i][j] == 3)
/*     */           {
/*     */             
/*  92 */             if (Snake.this.style == 1) {
/*     */               
/*  94 */               g.fillOval(i * 60, j * 60, 60, 120);
/*  95 */               g.clearRect(i * 60, j * 60 + 60, 60, 60);
/*     */             }
/*  97 */             else if (Snake.this.style == 2) {
/*     */               
/*  99 */               g.fillOval(i * 60, j * 60 - 60, 60, 120);
/* 100 */               g.clearRect(i * 60, j * 60 - 60, 60, 60);
/*     */             }
/* 102 */             else if (Snake.this.style == 3) {
/*     */               
/* 104 */               g.fillOval(i * 60, j * 60, 120, 60);
/* 105 */               g.clearRect(i * 60 + 60, j * 60, 60, 60);
/*     */             }
/* 107 */             else if (Snake.this.style == 4) {
/*     */               
/* 109 */               g.fillOval(i * 60 - 60, j * 60, 120, 60);
/* 110 */               g.clearRect(i * 60 - 60, j * 60, 60, 60);
/*     */             } else {
/*     */               
/* 113 */               g.fillOval(i * 60, j * 60, 60, 120);
/* 114 */               g.clearRect(i * 60, j * 60 + 60, 60, 60);
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/* 119 */       for (int i = 0; i < 13; i++) {
/*     */         
/* 121 */         for (int j = 0; j < 13; j++) {
/*     */ 
/*     */           
/* 124 */           if (Snake.this.location[i][j] == 1) {
/*     */             
/* 126 */             g.fillRect(i * 60, j * 60, 60, 60);
/*     */           }
/* 128 */           else if (Snake.this.location[i][j] == 2) {
/*     */             
/* 130 */             g.fillRect(i * 60 + 20, j * 60 + 20, 20, 20);
/*     */             
/* 132 */             g.fillRect(i * 60, j * 60, 60, 10);
/* 133 */             g.fillRect(i * 60, j * 60 + 50, 60, 10);
/* 134 */             g.fillRect(i * 60, j * 60 + 10, 10, 40);
/* 135 */             g.fillRect(i * 60 + 50, j * 60 + 10, 10, 40);
/* 136 */             g.drawLine(i * 60 + 30, j * 60, i * 60 + 30, j * 60 + 60);
/* 137 */             g.drawLine(i * 60, j * 60 + 30, i * 60 + 60, j * 60 + 30);
/*     */           }
/* 139 */           else if (Snake.this.location[i][j] == 4) {
/*     */             
/* 141 */             g.fillRect(i * 60 + 10, j * 60 + 10, 40, 40);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run(final JPanel panel) {
/* 152 */     this.pane = new mypanel();
/*     */ 
/*     */ 
/*     */     
/* 156 */     this.check = new JComboBox();
/* 157 */     this.check.addItem("简单");
/* 158 */     this.check.addItem("一般");
/* 159 */     this.check.addItem("困难");
/* 160 */     this.check.setFont(new Font("宋体", 0, 18));
/*     */     
/* 162 */     this.nandu = new JLabel("选择难度");
/* 163 */     this.nandu.setSize(100, 45);
/* 164 */     this.nandu.setFont(new Font("宋体", 0, 18));
/* 165 */     this.score = new JLabel("当前得分：");
/* 166 */     this.score.setSize(100, 45);
/* 167 */     this.score.setFont(new Font("宋体", 0, 18));
/*     */     
/* 169 */     this.up = new JButton("上");
/* 170 */     this.down = new JButton("下");
/* 171 */     this.left = new JButton("左");
/* 172 */     this.right = new JButton("右");
/* 173 */     this.start = new JButton("开始游戏");
/* 174 */     this.start.setFont(new Font("宋体", 0, 18));
/* 175 */     this.pause = new JButton("暂停");
/* 176 */     this.pause.setFont(new Font("宋体", 0, 18));
/* 177 */     this.up.setSize(60, 60);
/* 178 */     this.down.setSize(60, 60);
/* 179 */     this.left.setSize(60, 60);
/* 180 */     this.right.setSize(60, 60);
/* 181 */     this.start.setSize(120, 60);
/* 182 */     this.pause.setSize(60, 60);
/*     */     
/* 184 */     this.start.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/* 188 */             Snake.this.style = -1;
/* 189 */             Snake.this.mySnake = new MyStack();
/* 190 */             for (int i = 0; i < 13; i++) {
/*     */               
/* 192 */               for (int j = 0; j < 13; j++)
/*     */               {
/* 194 */                 Snake.this.location[i][j] = 0;
/*     */               }
/*     */             } 
/* 197 */             Snake.this.location[6][6] = 3;
/* 198 */             Snake.this.location[6][7] = 4;
/* 199 */             Snake.this.mySnake.add(new Snake.Point(Snake.this, 6, 7));
/* 200 */             Snake.this.mySnake.add(new Snake.Point(Snake.this, 6, 6));
/* 201 */             Snake.this.random();
/* 202 */             panel.repaint();
/* 203 */             Snake.this.score.setText("当前得分：");
/* 204 */             int model = Snake.this.check.getSelectedIndex();
/* 205 */             System.out.println("选中：" + model);
/* 206 */             if (model == 0) {
/*     */               
/* 208 */               Snake.this.sleepTime = 1000;
/*     */             }
/* 210 */             else if (model == 1) {
/*     */               
/* 212 */               Snake.this.sleepTime = 700;
/*     */             } else {
/*     */               
/* 215 */               Snake.this.sleepTime = 500;
/*     */             } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 223 */             while (Snake.this.isRun) {
/*     */               
/*     */               try {
/* 226 */                 Thread.sleep(200L);
/* 227 */               } catch (InterruptedException e1) {
/*     */                 
/* 229 */                 e1.printStackTrace();
/*     */               } 
/* 231 */               System.out.println("haha");
/*     */             } 
/* 233 */             Snake.this.style = 0;
/* 234 */             Snake.this.thread = new Snake.MoveThread(Snake.this);
/* 235 */             Snake.this.thread.start();
/* 236 */             Snake.this.start.setText("重新开始");
/*     */           }
/*     */         });
/* 239 */     this.pause.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/* 243 */             if (Snake.this.style == 5)
/*     */               return; 
/* 245 */             if (Snake.this.style == 0) {
/*     */               return;
/*     */             }
/*     */             
/* 249 */             if (Snake.this.style == -1) {
/*     */               return;
/*     */             }
/*     */ 
/*     */             
/* 254 */             Snake.this.style = 0;
/* 255 */             Snake.this.pause.setText("已暂停");
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 261 */     this.up.addActionListener(new ActionListener()
/*     */         {
/*     */           
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/* 266 */             if (Snake.this.style == 2 && Snake.this.mySnake.size() > 1)
/*     */               return; 
/* 268 */             Snake.this.style = 1;
/* 269 */             Snake.this.pause.setText("暂停");
/*     */           }
/*     */         });
/*     */     
/* 273 */     this.down.addActionListener(new ActionListener()
/*     */         {
/*     */           
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/* 278 */             if (Snake.this.style == 1 && Snake.this.mySnake.size() > 1)
/*     */               return; 
/* 280 */             Snake.this.style = 2;
/* 281 */             Snake.this.pause.setText("暂停");
/*     */           }
/*     */         });
/*     */     
/* 285 */     this.left.addActionListener(new ActionListener()
/*     */         {
/*     */           
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/* 290 */             if (Snake.this.style == 4 && Snake.this.mySnake.size() > 1)
/*     */               return; 
/* 292 */             Snake.this.style = 3;
/* 293 */             Snake.this.pause.setText("暂停");
/*     */           }
/*     */         });
/*     */     
/* 297 */     this.right.addActionListener(new ActionListener()
/*     */         {
/*     */ 
/*     */           
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/* 303 */             if (Snake.this.style == 3 && Snake.this.mySnake.size() > 1)
/*     */               return; 
/* 305 */             Snake.this.style = 4;
/* 306 */             Snake.this.pause.setText("暂停");
/*     */           }
/*     */         });
/*     */     
/* 310 */     panel.add(this.up);
/* 311 */     panel.add(this.down);
/* 312 */     panel.add(this.left);
/* 313 */     panel.add(this.right);
/* 314 */     panel.add(this.pane);
/* 315 */     panel.add(this.start);
/* 316 */     panel.add(this.pause);
/* 317 */     panel.add(this.score);
/* 318 */     panel.add(this.nandu);
/* 319 */     panel.add(this.check);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setbounds(int width, int height, int x, int y) {
/* 324 */     this.up.setBounds(845, 460, 60, 60);
/* 325 */     this.down.setBounds(845, 580, 60, 60);
/* 326 */     this.left.setBounds(785, 520, 60, 60);
/* 327 */     this.right.setBounds(905, 520, 60, 60);
/* 328 */     this.pane.setBounds(0, 0, 781, 781);
/* 329 */     this.start.setBounds(870, 720, 110, 60);
/* 330 */     this.pause.setBounds(780, 720, 90, 60);
/* 331 */     this.score.setBounds(800, 300, 200, 45);
/* 332 */     this.nandu.setBounds(800, 70, 200, 45);
/* 333 */     this.check.setBounds(800, 115, 120, 30);
/*     */   }
/*     */   
/*     */   public class Point {
/*     */     public int x;
/*     */     public int y;
/*     */     
/*     */     public Point(int x, int y) {
/* 341 */       this.x = x;
/* 342 */       this.y = y;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int up() {
/* 352 */     int size = this.mySnake.size();
/* 353 */     Point head = (Point)this.mySnake.see(size - 1);
/* 354 */     if (head.y == 0) {
/*     */       
/* 356 */       this.style = 5;
/* 357 */       return -1;
/*     */     } 
/*     */     
/* 360 */     this.style = 1;
/*     */     
/* 362 */     if (this.location[head.x][head.y - 1] == 1) {
/* 363 */       return 2;
/*     */     }
/* 365 */     if (this.location[head.x][head.y - 1] == 2) {
/*     */       
/* 367 */       this.mySnake.add(new Point(head.x, head.y - 1));
/* 368 */       this.location[head.x][head.y - 1] = 3;
/* 369 */       this.location[head.x][head.y] = 1;
/* 370 */       this.pane.repaint();
/* 371 */       return 1;
/*     */     } 
/* 373 */     Point tail = (Point)this.mySnake.see(0);
/*     */     
/* 375 */     this.location[tail.x][tail.y] = 0;
/* 376 */     Point ptail = (Point)this.mySnake.see(1);
/* 377 */     this.location[ptail.x][ptail.y] = 4;
/*     */     
/* 379 */     for (int len = 0; len < size - 1; len++) {
/*     */ 
/*     */       
/* 382 */       Point a = (Point)this.mySnake.see(len + 1);
/* 383 */       Point b = (Point)this.mySnake.see(len);
/*     */       
/* 385 */       if (a.x == b.x) {
/*     */         
/* 387 */         b.y = a.y;
/*     */       } else {
/*     */         
/* 390 */         b.x = a.x;
/*     */       } 
/*     */     } 
/*     */     
/* 394 */     if (this.location[head.x][head.y] == 3)
/* 395 */       this.location[head.x][head.y] = 1; 
/* 396 */     head.y--;
/* 397 */     this.location[head.x][head.y] = 3;
/*     */     
/* 399 */     this.pane.repaint();
/*     */     
/* 401 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int down() {
/* 406 */     int size = this.mySnake.size();
/* 407 */     Point head = (Point)this.mySnake.see(size - 1);
/* 408 */     if (head.y == 12) {
/*     */       
/* 410 */       this.style = 5;
/* 411 */       return -1;
/*     */     } 
/* 413 */     this.style = 2;
/*     */     
/* 415 */     if (this.location[head.x][head.y + 1] == 1) {
/* 416 */       return 2;
/*     */     }
/* 418 */     if (this.location[head.x][head.y + 1] == 2) {
/*     */       
/* 420 */       this.mySnake.add(new Point(head.x, head.y + 1));
/* 421 */       this.location[head.x][head.y + 1] = 3;
/* 422 */       this.location[head.x][head.y] = 1;
/* 423 */       this.pane.repaint();
/* 424 */       return 1;
/*     */     } 
/*     */     
/* 427 */     Point tail = (Point)this.mySnake.see(0);
/* 428 */     this.location[tail.x][tail.y] = 0;
/*     */     
/* 430 */     Point ptail = (Point)this.mySnake.see(1);
/* 431 */     this.location[ptail.x][ptail.y] = 4;
/*     */     
/* 433 */     for (int len = 0; len < size - 1; len++) {
/*     */ 
/*     */       
/* 436 */       Point a = (Point)this.mySnake.see(len + 1);
/* 437 */       Point b = (Point)this.mySnake.see(len);
/*     */       
/* 439 */       if (a.x == b.x) {
/*     */         
/* 441 */         b.y = a.y;
/*     */       } else {
/*     */         
/* 444 */         b.x = a.x;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 450 */     if (this.location[head.x][head.y] == 3) {
/* 451 */       this.location[head.x][head.y] = 1;
/*     */     }
/* 453 */     head.y++;
/* 454 */     this.location[head.x][head.y] = 3;
/*     */     
/* 456 */     this.pane.repaint();
/*     */     
/* 458 */     return 0;
/*     */   }
/*     */   
/*     */   public int left() {
/* 462 */     int size = this.mySnake.size();
/* 463 */     Point head = (Point)this.mySnake.see(size - 1);
/* 464 */     if (head.x == 0) {
/*     */       
/* 466 */       this.style = 5;
/* 467 */       return -1;
/*     */     } 
/* 469 */     this.style = 3;
/* 470 */     if (this.location[head.x - 1][head.y] == 1) {
/* 471 */       return 2;
/*     */     }
/* 473 */     if (this.location[head.x - 1][head.y] == 2) {
/*     */       
/* 475 */       this.mySnake.add(new Point(head.x - 1, head.y));
/* 476 */       this.location[head.x - 1][head.y] = 3;
/* 477 */       this.location[head.x][head.y] = 1;
/* 478 */       this.pane.repaint();
/* 479 */       return 1;
/*     */     } 
/*     */     
/* 482 */     Point tail = (Point)this.mySnake.see(0);
/* 483 */     this.location[tail.x][tail.y] = 0;
/*     */     
/* 485 */     Point ptail = (Point)this.mySnake.see(1);
/* 486 */     this.location[ptail.x][ptail.y] = 4;
/*     */     
/* 488 */     for (int len = 0; len < size - 1; len++) {
/*     */ 
/*     */       
/* 491 */       Point a = (Point)this.mySnake.see(len + 1);
/* 492 */       Point b = (Point)this.mySnake.see(len);
/*     */       
/* 494 */       if (a.x == b.x) {
/*     */         
/* 496 */         b.y = a.y;
/*     */       } else {
/*     */         
/* 499 */         b.x = a.x;
/*     */       } 
/*     */     } 
/*     */     
/* 503 */     if (this.location[head.x][head.y] == 3)
/* 504 */       this.location[head.x][head.y] = 1; 
/* 505 */     head.x--;
/* 506 */     this.location[head.x][head.y] = 3;
/*     */     
/* 508 */     this.pane.repaint();
/*     */     
/* 510 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int right() {
/* 515 */     int size = this.mySnake.size();
/* 516 */     Point head = (Point)this.mySnake.see(size - 1);
/* 517 */     if (head.x == 12) {
/*     */       
/* 519 */       this.style = 5;
/* 520 */       return -1;
/*     */     } 
/* 522 */     this.style = 4;
/* 523 */     if (this.location[head.x + 1][head.y] == 1) {
/* 524 */       return 2;
/*     */     }
/* 526 */     if (this.location[head.x + 1][head.y] == 2) {
/*     */       
/* 528 */       this.mySnake.add(new Point(head.x + 1, head.y));
/* 529 */       this.location[head.x + 1][head.y] = 3;
/* 530 */       this.location[head.x][head.y] = 1;
/* 531 */       this.pane.repaint();
/* 532 */       return 1;
/*     */     } 
/*     */     
/* 535 */     Point tail = (Point)this.mySnake.see(0);
/* 536 */     this.location[tail.x][tail.y] = 0;
/*     */     
/* 538 */     Point ptail = (Point)this.mySnake.see(1);
/* 539 */     this.location[ptail.x][ptail.y] = 4;
/*     */     
/* 541 */     for (int len = 0; len < size - 1; len++) {
/*     */ 
/*     */       
/* 544 */       Point a = (Point)this.mySnake.see(len + 1);
/* 545 */       Point b = (Point)this.mySnake.see(len);
/*     */       
/* 547 */       if (a.x == b.x) {
/*     */         
/* 549 */         b.y = a.y;
/*     */       } else {
/*     */         
/* 552 */         b.x = a.x;
/*     */       } 
/*     */     } 
/*     */     
/* 556 */     if (this.location[head.x][head.y] == 3)
/* 557 */       this.location[head.x][head.y] = 1; 
/* 558 */     head.x++;
/* 559 */     this.location[head.x][head.y] = 3;
/*     */     
/* 561 */     this.pane.repaint();
/*     */     
/* 563 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class MoveThread
/*     */     extends Thread
/*     */   {
/*     */     public void run() {
/* 573 */       Snake.this.isRun = true;
/*     */ 
/*     */       
/*     */       while (true) {
/* 577 */         int k = 0;
/* 578 */         if (Snake.this.style == 1) {
/*     */           
/* 580 */           k = Snake.this.up();
/* 581 */           if (k == 1) {
/* 582 */             Snake.this.random();
/* 583 */             int soc = Snake.this.mySnake.size() - 2;
/* 584 */             Snake.this.score.setText("当前得分：" + soc);
/*     */           }
/* 586 */           else if (k == 2 || k == -1) {
/*     */             
/* 588 */             int soc = Snake.this.mySnake.size() - 2;
/* 589 */             Snake.this.score.setText("最终得分：" + soc);
/*     */             
/*     */             return;
/*     */           } 
/* 593 */         } else if (Snake.this.style == 2) {
/*     */           
/* 595 */           k = Snake.this.down();
/* 596 */           if (k == 1) {
/*     */             
/* 598 */             Snake.this.random();
/* 599 */             int soc = Snake.this.mySnake.size() - 2;
/* 600 */             Snake.this.score.setText("当前得分：" + soc);
/*     */           }
/* 602 */           else if (k == 2 || k == -1) {
/*     */             
/* 604 */             int soc = Snake.this.mySnake.size() - 2;
/* 605 */             Snake.this.score.setText("最终得分：" + soc);
/*     */             
/*     */             return;
/*     */           } 
/* 609 */         } else if (Snake.this.style == 3) {
/*     */           
/* 611 */           k = Snake.this.left();
/* 612 */           if (k == 1) {
/*     */             
/* 614 */             Snake.this.random();
/* 615 */             int soc = Snake.this.mySnake.size() - 2;
/* 616 */             Snake.this.score.setText("当前得分：" + soc);
/*     */           }
/* 618 */           else if (k == 2 || k == -1) {
/*     */             
/* 620 */             int soc = Snake.this.mySnake.size() - 2;
/* 621 */             Snake.this.score.setText("最终得分：" + soc);
/*     */             
/*     */             return;
/*     */           } 
/* 625 */         } else if (Snake.this.style == 4) {
/*     */           
/* 627 */           k = Snake.this.right();
/* 628 */           if (k == 1) {
/*     */             
/* 630 */             Snake.this.random();
/* 631 */             int soc = Snake.this.mySnake.size() - 2;
/* 632 */             Snake.this.score.setText("当前得分：" + soc);
/*     */           }
/* 634 */           else if (k == 2 || k == -1) {
/*     */             
/* 636 */             int soc = Snake.this.mySnake.size() - 2;
/* 637 */             Snake.this.score.setText("最终得分：" + soc);
/*     */             
/*     */             return;
/*     */           } 
/* 641 */         } else if (Snake.this.style == 0) {
/* 642 */           System.out.println("waiting..");
/* 643 */         } else if (Snake.this.style == -1) {
/*     */           
/* 645 */           Snake.this.isRun = false;
/*     */           return;
/*     */         } 
/*     */         try {
/* 649 */           System.out.println("sleep time:" + Snake.this.sleepTime);
/* 650 */           Thread.sleep(Snake.this.sleepTime);
/* 651 */         } catch (InterruptedException e) {
/* 652 */           e.printStackTrace();
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void init() {
/* 659 */     for (int i = 0; i < this.mySnake.size(); i++) {
/*     */       
/* 661 */       Point p = (Point)this.mySnake.see(i);
/* 662 */       this.location[p.x][p.y] = 1;
/*     */     } 
/* 664 */     this.pane.repaint();
/*     */   }
/*     */   
/*     */   public int random() {
/* 668 */     int x = this.random.nextInt(13);
/* 669 */     int y = this.random.nextInt(13);
/*     */ 
/*     */ 
/*     */     
/* 673 */     while (this.location[x][y] == 1) {
/*     */       
/* 675 */       x = this.random.nextInt(13);
/* 676 */       y = this.random.nextInt(13);
/*     */     } 
/*     */     
/* 679 */     this.location[x][y] = 2;
/* 680 */     this.pane.repaint();
/*     */     
/* 682 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\xianzhikun\Desktop\mytemple\GluttonousSnake\GluttonousSnake.jar!\test\Snake.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */