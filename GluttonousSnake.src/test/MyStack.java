/*    */ package test;
/*    */ 
/*    */ public class MyStack {
/*  4 */   private Object[] data = new Object[0];
/*    */   
/*    */   public void add(Object a) {
/*  7 */     Object[] copy = new Object[this.data.length + 1];
/*  8 */     for (int i = 0; i < this.data.length; i++)
/*    */     {
/* 10 */       copy[i] = this.data[i];
/*    */     }
/* 12 */     copy[this.data.length] = a;
/* 13 */     this.data = copy;
/*    */   }
/*    */   
/*    */   public Object get() throws Exception {
/* 17 */     if (this.data.length == 0)
/*    */     {
/* 19 */       throw new Exception("the stack is overflow");
/*    */     }
/* 21 */     Object[] copy = new Object[this.data.length - 1];
/* 22 */     for (int i = 0; i < copy.length; i++)
/*    */     {
/* 24 */       copy[i] = this.data[i];
/*    */     }
/* 26 */     Object val = this.data[this.data.length - 1];
/* 27 */     this.data = copy;
/* 28 */     return val;
/*    */   }
/*    */   
/*    */   public Object see() throws Exception {
/* 32 */     if (this.data.length == 0)
/*    */     {
/* 34 */       throw new Exception("the stack is overflow");
/*    */     }
/* 36 */     return this.data[this.data.length - 1];
/*    */   }
/*    */   
/*    */   public Object see(int index) {
/* 40 */     if (this.data.length == 0) {
/*    */       
/*    */       try {
/* 43 */         throw new Exception("the stack is overflow");
/* 44 */       } catch (Exception e) {
/*    */         
/* 46 */         e.printStackTrace();
/*    */       } 
/*    */     }
/* 49 */     return this.data[index];
/*    */   }
/*    */   
/*    */   public void print() {
/* 53 */     for (int i = 0; i < this.data.length; i++)
/*    */     {
/* 55 */       System.out.print("data " + i + "=" + this.data[i].toString() + "  ");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/* 60 */   public int size() { return this.data.length; }
/*    */ 
/*    */ 
/*    */   
/* 64 */   public boolean isempty() { return (this.data.length == 0); }
/*    */ }


/* Location:              C:\Users\xianzhikun\Desktop\mytemple\GluttonousSnake\GluttonousSnake.jar!\test\MyStack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */