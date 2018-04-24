#include "queue.h"

int flag;
Queue Q;

int main(){

    InitQueue(&Q);
	int num;
	while (1) {
        int i = 1;
        printf("只支持以下三种数据类型！\n");
        printf("*******************************\n");
        printf("*           1----整型         *\n");
        printf("*           2----浮点型       *\n");
        printf("*           3----字符型       *\n");
        printf("*           0----退出         *\n");
        printf("*******************************\n");
        printf("请输入你的选择：");
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
                printf("输入第%d个元素（以0为结束）：", i);
                scanf("%d", n);
                while (*n != 0) {
                    EnQueue(&Q, n);
                    i++;
                    n = (int *)malloc(sizeof(int));
                    printf("输入第%d个元素（以0为结束）：", i);
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
                printf("输入第%d个元素（以0为结束）：", i);
                scanf("%f", f);

                while (*f != 0.0) {
                    EnQueue(&Q, f);
                    i++;
                    f = (float *)malloc(sizeof(float));
                    printf("输入第%d个元素（以0为结束）：", i);
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
                printf("输入第%d个元素（以#为结束且為單個字符）：", i);
                scanf("%c", c);
                getchar();
                while (*c != '#') {
                    EnQueue(&Q, c);
                    i++;
                    c = (char *)malloc(sizeof(char));
                    printf("输入第%d个元素（以#为结束且為單個字符）：", i);
                    scanf("%c", c);
                    getchar();
                }
                system("pause");
                system("cls");
                select();
                break;
            } default: printf("输入不正确，请退出！\n");
                       break;
        }
	}

    getch();
	return 0;
}
