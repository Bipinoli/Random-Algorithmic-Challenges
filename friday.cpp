/*
ID: bipinol2
TASK: friday
LANG: C++
*/

#include<bits/stdc++.h>

using namespace std;

bool isLeapYear(int n){
	return (n%400 == 0 or (n%4==0 and n%100!=0))?true:false;
}

int main(){

	//freopen("friday.in",'r',stdin);
	//freopen("friday.out",'w',stdout);

	int regular[] = {31,28,31,30,31,30,31,31,30,31,30,31};
	int leap[] = {31,29,31,30,31,30,31,31,30,31,30,31};
	int weekDay[] = {0,0,0,0,0,0,0};//starts with Saturday
	
	// 1900 1st january is Monday
	int n;
	cin >> n;
	int first13 = 0; //Saturday
	int val=0; //date if 1900 was the 0 AD
	for (int i=0; i<n; i++){
		int* arrPtr;
		if (isLeapYear(1900+i)) {
			arrPtr = leap;
			cout<<1900+i<<" is a leap year. Feb has "<<arrPtr[1]<<" days"<<endl;
		}
		else {
			arrPtr = regular;
			cout<<1900+i<<" is not a leap year. Feb has "<<arrPtr[1]<<" days"<<endl;
		}
		for (int j=0; j<12; j++){
			val+=arrPtr[j];
			int steps = val%7;
			first13 += steps;
			first13 %= 7;
			weekDay[first13] += 1;
		}	
	}

	for (int i=0; i<7; i++)
		cout<<weekDay[i]<<" ";
	cout<<endl;	

	return 0;
}


