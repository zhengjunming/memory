#include "BinaryTree.h"

BiTree T;

int main() {
    BiTNode p;
    TElemType *q;
    T = &p;
    TElemType a[100];
    T = (BiTNode *)malloc(sizeof(BiTNode));
    InitBiTree(T);
    printf("������һ����ȷ��ǰ׺���ʽ���벻Ҫ����\n��������Ͳ�����֮��ķ��ţ������ֹͣ���У�\n");
    printf("�����м���ǰ׺���ʽ���ӣ�/*123  -+-/123*45*67    -+*1+2345\n");
    gets(a);
    q = a;
    CreateBiTree(T, &q);
    printf("===================\n");
    printf("\nǰ�������");
    PreOrderTraverse(T, visit);
    printf("\n���������");
    InOrderTraverse(T, visit);
    printf("\n���������");
    PostOrderTraverse(T, visit);
    printf("\n��α�����");
    PrintNodeByLevel(T);
    printf("\n��������ȣ�");
    printf("%d", BiTreeDepth(T));
    DestroyBiTree(T);
    getch();
    return 0;
}
