package com.algorithm.dynamiclink;

/**
 * @Author:artorias
 * @Description:
 * @Date:create in 13:49 2018/8/24 0024
 * 动态连通性问题
 */
public class DynamicLink {
    //分量id
    private int[] id;
    //分量数量
    private int count;
    //树的大小
    private int[] sz;

    public DynamicLink(int n){
        count=n;
        id=new int[n];
        for (int i=0;i<n;i++){
            id[i]=i;
        }
        sz=new int[n];
        for (int i=0;i<n;i++){
            sz[i]=1;
        }
    }

     public int count(){
         return count;
     }

    public void union(int p,int q){
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot==qRoot){
            return;
        }
        //总是将小树嫁接到大树上
        if (sz[pRoot]<sz[qRoot]){
            id[pRoot]=qRoot;
            sz[qRoot]=sz[qRoot]+sz[pRoot];
        }else {
            id[qRoot]=pRoot;
            sz[pRoot]=sz[pRoot]+sz[qRoot];
        }
        count--;
    }

    public int find(int p){
         while (id[p]!=p){
             p=id[p];
         }
         return p;
    }

    public boolean connected(int p,int q){
         if (find(p)==find(q)){
             return true;
         }
         return false;
    }

    public int[] id(){
        return id;
    }
}
