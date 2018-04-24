#include "stack.h"
extern int flag;

/**
*@Name..............: initStack
*@Description.....: ��ʼ��һ��ջ
*@Parameters.....: S:һ��������
*@Return values.: ����OK(��ʾ��ʼ�����)
*@notice.............:��
*/
Status initStack(LinkStack *S) {
    S->top = NULL;
    S->count = 0;
    flag = 1;
    return OK;
}

/**
*@Name..............: isEmpty
*@Description.....: �ж��Ƿ�Ϊ��ջ
*@Parameters.....: S��ջ��ָ��
*@Return values.: Ϊ�շ���TRUE�����򷵻�FALSE
*/
Status isEmpty(LinkStack *S) {
    return (NULL == S->top ? TRUE : FALSE);
}

/**
*@Name..............: push
*@Description.....: ��һ��Ԫ��ѹ��ջ��
*@Parameters.....: S��ջ��ָ�룬e����ѹԪ��
*@Return values.: �ɹ��򷵻�OK�������ڴ�ʧ���򷵻�OVERFLOW
*/
Status push(LinkStack *S, ElemType e) {
    StackNode *p = (StackNode *)malloc(sizeof(StackNode));
    if (NULL == p) {
        return OVERFLOW;
    }
    p->data = e;
    p->next = S->top;//���½��*p������ջͷ��
    S->top = p;
    S->count++;
    return OK;
}

/**
*@Name..............: pop
*@Description.....: ��ջ��Ԫ�س�ջ
*@Parameters.....: S:ջ��ָ�룬e:ElemTypeָ��
*@Return values.: �ɹ��򷵻�OK��ʧ���򷵻�ERROR
*/
Status pop(LinkStack *S, ElemType *e) {
    if (isEmpty(S))
        return ERROR;  //����
    StackNode *p = S->top;//����ջ��ָ��
    *e = p->data;  //����ջ���������
    S->top = p->next;  //��ջ����������ժ��
    free(p);
    S->count--;
    return OK;
}

/**
*@Name..............: getTop
*@Description.....: ��ȡջ��Ԫ��ֵ
*@Parameters.....: S��ջ��ָ�룬e��ElemTypeָ�루���Ԫ��ֵ��
*@Return values.: �ɹ��򷵻�OK��ʧ���򷵻�ERROR
*/
Status getTop(LinkStack *S, ElemType *e) {
    if (isEmpty(S)) {
        return ERROR;
    }
    *e = S->top->data;
    return OK;
}

/**
*@Name..............: destroyStack
*@Description.....: ����һ��ջ
*@Parameters.....: S��ջ��ָ��
*@Return values.: ����OK
*/
Status destroyStack(LinkStack *S) {
    if (isEmpty(S)) {
        free(S);
        flag = 0;
        return OK;
    }
    clear(S);
    free(S);
    flag = 0;
    return OK;
}

/**
*@Name..............: stackLength
*@Description.....: ��ȡջ�ĳ���
*@Parameters.....: S��ջ��ָ��
*@Return values.: ����ջ�ĳ���
*/
Status stackLength(LinkStack *S) {
    return S->count;
}

/**
*@Name..............: clear
*@Description.....: ���һ��ջ
*@Parameters.....: S��ջ��ָ��
*@Return values.: �ɹ��򷵻�OK��ʧ���򷵻�ERROR
*/
Status clear(LinkStack *S) {
    ElemType *e;
    e = (ElemType *)malloc(sizeof(ElemType));
    if (NULL == e) {
        return ERROR;
    }
    while (pop(S, e) != ERROR) {
        printf("���������Ԫ�أ�%d\n", *e);
    }
    S->count = 0;
    return OK;
}

/**
*@Name..............: printStack
*@Description.....: ��ӡһ��ջ
*@Parameters.....: S��ջ��ָ��
*@Return values.: ��
*/
void printStack(LinkStack *S) {
    StackNode *p;
    p = S->top;
    printf("ջ��Ԫ��Ϊ��\n");
    printf("ջ��-- ");
    while (p != NULL) {
        printf("%d -- ", p->data);
        p = p->next;
    }
    printf("ջ��");
    printf("\n");
}

/**
*@Name..............: select
*@Description.....: ���в˵�ѡ��
*@Parameters.....: ��
*@Return values.: ��
*/
void select() {
    int selectNum;
    int a;
    LinkStack Sq;
    LinkStack *S = &Sq;
    ElemType *e, *ele;
    e = (ElemType *)malloc(sizeof(ElemType));
    ele = (ElemType *)malloc(sizeof(ElemType));
    ElemType elem;
    while (1) {
        printf("*****************˳��ջ�Ĳ���*****************\n");
        printf("*              1-----ջ�ĳ�ʼ��              *\n");
        printf("*              2-----ѹջ����                *\n");
        printf("*              3-----��ջ����                *\n");
        printf("*              4-----��ȡջ��Ԫ��            *\n");
        printf("*              5-----�ж��Ƿ��ǿ�ջ          *\n");
        printf("*              6-----����һ��ջ              *\n");
        printf("*              7-----��ӡһ��ջ              *\n");
        printf("*              8-----��ջ�ĳ���              *\n");
        printf("*              9-----���һ����ջ            *\n");
        printf("*              0-----�˳�����                *\n");
        printf("**********************************************\n");
        printf("������������еĲ���:");
        scanf("%d", &selectNum);
        getchar();
        if (selectNum == 0) {
            break;
        }
        switch (selectNum) {
            case 1: {
                if (flag == 1) {
                    printf("ջ�Ѿ���ʼ���������ظ���ʼ����\n");
                } else {
                    a = initStack(S);
                    if (a == OVERFLOW) {
                        printf("ջ��ʼ��ʧ��\n");
                    } else {
                        printf("ջ��ʼ���ɹ�\n");
                    }
                }
                break;
            }
            case 2: {
                if (flag == 0) {
                    printf("ջ��δ��ʼ�������Ƚ���ջ��ʼ��������\n");
                } else {
                    printf("�������ջ��Ԫ�أ�");
                    scanf("%d", &elem);
                    a = push(S, elem);
                    if (a == 1) {
                        printf("ѹջ�ɹ���\n");
                    } else {
                        printf("ѹջʧ��\n");
                    }
                }
                break;
            }
            case 3: {
                if (flag == 0) {
                    printf("ջ��δ��ʼ�������Ƚ���ջ��ʼ��������\n");
                } else if (isEmpty(S)) {
                    printf("��ջΪ��ջ��û����ջԪ�أ�\n");
                } else {
                    a = pop(S, e);
                    if (a == 1) {
                        printf("��ջ�ɹ�����ջԪ��Ϊ��%d\n", *e);
                    } else {
                        printf("��ջʧ�ܣ�\n");
                    }
                }
                break;
            }
            case 4: {
                if (flag == 0) {
                    printf("ջ��δ��ʼ�������Ƚ���ջ��ʼ��������\n");
                } else if (isEmpty(S)) {
                    printf("��ջΪ��ջ��û��ջ��Ԫ��!\n");
                } else {
                    a = getTop(S, ele);
                    if (a == 1) {
                        printf("ջ��Ԫ��Ϊ��%d\n", *ele);
                    } else {
                        printf("��ջ��δ��ʼ�������ȳ�ʼ����\n");
                    }
                }
                break;
            }
            case 5: {
                if (flag == 0) {
                    printf("ջ��δ��ʼ�������ȳ�ʼ����\n");
                } else {
                    a = isEmpty(S);
                    if (a == 1) {
                        printf("��ջ�ǿ�ջ��\n");
                    } else {
                        printf("��ջ���ǿ�ջ��\n");
                    }
                }
                break;
            }
            case 6: {
                if (flag == 0) {
                    printf("ջ��δ��ʼ�����������٣�\n");
                } else {
                    a = destroyStack(S);
                    if (a == 1) {
                        printf("���ٳɹ���\n");
                    }
                }
                break;
            }
            case 7: {
                if (flag == 0) {
                    printf("ջ��û��ʼ�����޷���ӡ��\n");
                } else if (isEmpty(S)) {
                    printf("��ջΪ��ջ���޷���ӡ��\n");
                } else {
                    printStack(S);
                }
                break;
            }
            case 8: {
                if (flag == 0) {
                    printf("ջ��û��ʼ����û�г��ȣ�\n");
                } else {
                    printf("����Ϊ��%d\n", S->count);
                }
                break;
            }
            case 9: {
                if (flag == 0) {
                    printf("ջ��δ��ʼ�������������\n");
                } else {
                    a = clear(S);
                    if (a == OK) {
                        printf("����ɹ���\n");
                    }
                }
                break;
            }
            default: printf("��������ȷ��ֵ��\n");
                break;
        }
        system("pause");
        system("cls");
    }
}
