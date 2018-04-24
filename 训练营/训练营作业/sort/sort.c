#include "sort.h"

extern long int MAX_SIZE;
extern LinkStack S;


void qSort_Recursion(ElemType a[], int left, int right) {
    ElemType *e, *ele;
    e = (ElemType *)malloc(sizeof(ElemType));
    ele = (ElemType *)malloc(sizeof(ElemType));
    int i;
    push2(left, right);
    while (!isEmpty(&S)) {
        pop(&S, e);
        pop(&S, ele);
        left = *e;
        right = *ele;
        if (right <= left) {
            continue;
        }
        i = partition(a, left, right);
        if (i - left > right - i) {
            push2(left, i - 1);
            push2(i + 1, right);
        } else {
            push2(i + 1, right);
            push2(left, i - 1);
        }

    }
}

int partition(ElemType a[], int left, int right) {
    int i = left - 1;
    int j = right;
    ElemType s = a[right];
    while (1) {
        while (a[++i] < s);
        while (a[--j] > s)
            if (j == 1)
                break;
        if (i >= j)
            break;
        SWAP(a[i], a[j]);
    }
    SWAP(a[i], a[right]);

    return i;
}

void insertSort(int a[]) {
    int i, j, temp;
    for (j = 1; j < MAX_SIZE; j++) {
        temp = a[j];
        i = j - 1;
        while (temp < a[i]) {
            a[i + 1] = a[i];
            i--;
            if (i == -1) {
                break;
            }
        }
        a[i + 1] = temp;
    }

}

void qSort(int a[], int left, int right) {
    int i;
    if (right <= left)
        return;
    i = partition(a, left, right);
    qSort(a, left, i - 1);
    qSort(a, i + 1, right);
}

void mergeSort(int a[], int left, int right){
    if (left < right) {
        int mid;
        mid = (right + left) / 2;
        mergeSort(a, left, mid);
        mergeSort(a, mid + 1, right);
        Merge(a, left, mid, right);
    }
}

void Merge(int a[], int left, int mid, int rigth) {
    int aux[MAX_SIZE];
    int i, j, k;
    for (i = mid + 1; i > left; i--) {
        aux[i - 1] = a[i - 1];
    }
    for (j = mid; j < rigth; j++) {
        aux[rigth + mid - j] = a[j + 1];
    }
    for (k = left; k <= rigth; k++) {
        if (aux[j] < aux[i]) {
            a[k] = aux[j--];
        } else {
            a[k] = aux[i++];
        }
    }
}
