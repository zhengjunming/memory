#include "queue.h"

int flag;
Queue Q;

int main(){

    InitQueue(&Q);
	int num;
	while (1) {
        int i = 1;
        printf("ֻ֧�����������������ͣ�\n");
        printf("*******************************\n");
        printf("*           1----����         *\n");
        printf("*           2----������       *\n");
        printf("*           3----�ַ���       *\n");
        printf("*           0----�˳�         *\n");
        printf("*******************************\n");
        printf("���������ѡ��");
        scanf("%d", &num);
        getchar();
        if (num == 0) {
            DestoryQueue(&Q);
            break;
        }
        switch (num) {
            case 1: {
                flag = INT;
                int *n = NULL;
                Q.data_size = sizeof(int);
                n = (int *)malloc(sizeof(int));
                printf("�����%d��Ԫ�أ���0Ϊ��������", i);
                scanf("%d", n);
                while (*n != 0) {
                    EnQueue(&Q, n);
                    i++;
                    n = (int *)malloc(sizeof(int));
                    printf("�����%d��Ԫ�أ���0Ϊ��������", i);
                    scanf("%d", n);
                }
                system("pause");
                system("cls");
                select();
                break;
            } case 2: {
                flag = FLOAT;
                float *f = NULL;
                Q.data_size = sizeof(float);
                f = (float *)malloc(sizeof(float));
                printf("�����%d��Ԫ�أ���0Ϊ��������", i);
                scanf("%f", f);

                while (*f != 0.0) {
                    EnQueue(&Q, f);
                    i++;
                    f = (float *)malloc(sizeof(float));
                    printf("�����%d��Ԫ�أ���0Ϊ��������", i);
                    scanf("%f", f);

                }
                system("pause");
                system("cls");
                select();
                break;
            } case 3: {
                flag = CHAR;
                char *c = NULL;
                Q.data_size = sizeof(char);
                c = (char *)malloc(sizeof(char));
                printf("�����%d��Ԫ�أ���#Ϊ�����Ҟ�΂��ַ�����", i);
                scanf("%c", c);
                getchar();
                while (*c != '#') {
                    EnQueue(&Q, c);
                    i++;
                    c = (char *)malloc(sizeof(char));
                    printf("�����%d��Ԫ�أ���#Ϊ�����Ҟ�΂��ַ�����", i);
                    scanf("%c", c);
                    getchar();
                }
                system("pause");
                system("cls");
                select();
                break;
            } default: printf("���벻��ȷ�����˳���\n");
                       break;
        }
	}

    getch();
	return 0;
}
