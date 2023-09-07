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
	freopen("friday.in", "r", stdin);
  	freopen("friday.out", "w", stdout);

	int regular[] = {31,28,31,30,31,30,31,31,30,31,30,31};
	int leap[] = {31,29,31,30,31,30,31,31,30,31,30,31};
	int weekDay[] = {0,0,0,0,0,0,0};//starts with Saturday
	
	// 1900 1st january is Monday
	int n;
	cin >> n;
	int first13 = 0; //Saturday
	weekDay[0] = 1; //this is the first 13th
	for (int i=0; i<n; i++){
		int* arrPtr;
		if (isLeapYear(1900+i)) arrPtr = leap;
		else arrPtr = regular;

		for (int j=0; j<12; j++){
			first13 += arrPtr[j];
			first13 %= 7;
			weekDay[first13] += 1;
		}	
	}

	weekDay[first13]--; //one more has been added when there was no next month 

	cout<<weekDay[0];
	for (int i=1; i<7; i++)
		cout<<" "<<weekDay[i];
	cout<<endl;

	return 0;
}


