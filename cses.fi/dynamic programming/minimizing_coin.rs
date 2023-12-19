use std::io;
use std::cmp;

fn read_line_as_vec() -> Vec<i64> {
    let mut line = String::new();
    let _ = io::stdin().read_line(&mut line);
    line.trim().split(' ').map(|i| i.parse::<i64>().unwrap()).collect()
}

fn main() {
    let sum = {
        let v = read_line_as_vec();
        v[1]
    };
    let coins = read_line_as_vec();

    let mut best: Vec<i64> = vec![0; (sum + 1) as usize];

    for s in 1..=sum {
        let mut s_best = i64::MAX;
        for c in &coins {
            if s - c >= 0 && best[(s-c) as usize] != i64::MAX {
                s_best = cmp::min(s_best, 1 + best[(s-c) as usize]);
            }
        }
        best[s as usize] = s_best;
    }

    let ans = {
        let ans = best[sum as usize];
        if ans == i64::MAX {
            -1
        } else {
            ans
        }
    };

    // dbg!(&best);

    println!("{ans}");
}