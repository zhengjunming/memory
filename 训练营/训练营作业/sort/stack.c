#include "sort.h"


Status initStack(LinkStack *S) {
    S->top = NULL;
    return OK;
}

Status isEmpty(LinkStack *S) {
    return (NULL == S->top ? TRUE : FALSE);
}

Status push(LinkStack *S, ElemType e) {
    StackNode *p = (StackNode *)malloc(sizeof(StackNode));
    if (NULL == p) {
        return OVERFLOW;
    }
    p->data = e;
    p->next = S->top;//将新结点*p插入链栈头部
    S->top = p;
    return OK;
}

Status pop(LinkStack *S, ElemType *e) {
    if (isEmpty(S))
        return ERROR;  //下溢
    StackNode *p = S->top;//保存栈顶指针
    *e = p->data;  //保存栈顶结点数据
    S->top = p->next;  //将栈顶结点从链上摘下
    free(p);
    return OK;
}

Status destroyStack(LinkStack *S) {
    if (isEmpty(S)) {
        free(S);
        return OK;
    }
    clear(S);
    free(S);
    return OK;
}

Status clear(LinkStack *S) {
    while (pop(S, NULL) != ERROR);
    return OK;
}
