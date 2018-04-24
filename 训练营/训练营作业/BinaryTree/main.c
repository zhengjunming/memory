#include "BinaryTree.h"

BiTree T;

int main() {
    BiTNode p;
    TElemType *q;
    T = &p;
    TElemType a[100];
    T = (BiTNode *)malloc(sizeof(BiTNode));
    InitBiTree(T);
    printf("请输入一个正确的前缀表达式，请不要输入\n除运算符和操作符之外的符号，否则会停止运行！\n");
    printf("这里有几个前缀表达式例子：/*123  -+-/123*45*67    -+*1+2345\n");
    gets(a);
    q = a;
    CreateBiTree(T, &q);
    printf("===================\n");
    printf("\n前序遍历：");
    PreOrderTraverse(T, visit);
    printf("\n中序遍历：");
    InOrderTraverse(T, visit);
    printf("\n后序遍历：");
    PostOrderTraverse(T, visit);
    printf("\n层次遍历：");
    PrintNodeByLevel(T);
    printf("\n二叉树深度：");
    printf("%d", BiTreeDepth(T));
    DestroyBiTree(T);
    getch();
    return 0;
}
