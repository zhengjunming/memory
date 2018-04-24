#include "sort.h"

LinkStack S;

long int MAX_SIZE;
int main()
{
    initStack(&S);
    FILE *fp;
    fp = fopen("total.txt", "wb+");
    printf("������������ļ������������Ŀ��");
    int a;
    int i;
    scanf("%d", &a);
    fflush(stdin);
    srand(time(NULL));
    for (i = 0; i < a; i++) {
        fprintf(fp, "%d ", rand() % 1000);
    }

    srand(time(NULL));
    printf("���������������������С��");
    scanf("%ld", &MAX_SIZE);
    //fflush(stdin);
    ElemType number[MAX_SIZE];

    // ��������
    rewind(fp);
    for (i = 0; i < MAX_SIZE; i++) {
        fscanf(fp, "%d ", &number[i]);
    }
    clock_t start, stop;
    double duration;
    start = clock();
    insertSort(number);
    stop = clock();
    duration = ((double)(stop - start));
    printf("��������%f����\n", duration);
    FILE *fp1;
    fp1 = fopen("insertSort.txt", "w+");
    for (i = 0; i < MAX_SIZE; i++) {
        fprintf(fp1, "%d ", number[i]);
    }
    fclose(fp1);

    // ��������
    rewind(fp);
    for (i = 0; i < MAX_SIZE; i++) {
        fscanf(fp, "%d ", &number[i]);
    }
    start = clock();
    qSort(number, 0, MAX_SIZE - 1);
    stop = clock();
    duration = ((double)(stop - start));
    printf("��������%f����\n", duration);
    FILE *fp2;
    fp2 = fopen("qSort.txt", "w+");
    for (i = 0; i < MAX_SIZE; i++) {
        fprintf(fp2, "%d ", number[i]);
    }
    fclose(fp2);

    // �鲢����
    rewind(fp);
    for (i = 0; i < MAX_SIZE; i++) {
        fscanf(fp, "%d ", &number[i]);
    }
    start = clock();
    mergeSort(number, 0, MAX_SIZE - 1);
    stop = clock();
    duration = ((double)(stop - start));
    printf("�鲢����%f����\n", duration);
    FILE *fp3;
    fp3 = fopen("MergeSort.txt", "w+");
    for (i = 0; i < MAX_SIZE; i++) {
        fprintf(fp3, "%d ", number[i]);
    }
    fclose(fp3);

    // ��������ǵݹ�
    rewind(fp);
    for (i = 0; i < MAX_SIZE; i++) {
        fscanf(fp, "%d ", &number[i]);
    }
    start = clock();
    qSort_Recursion(number, 0, MAX_SIZE - 1);
    stop = clock();
    duration = ((double)(stop - start));
    printf("���ŷǵݹ飺%f����\n", duration);
    FILE *fp4;
    fp4= fopen("qSort_Recursion.txt", "w+");
    for (i = 0; i < MAX_SIZE; i++) {
        fprintf(fp4, "%d ", number[i]);
    }
    fclose(fp4);
    destroyStack(&S);

    fclose(fp);
    getch();
    return 0;
}

