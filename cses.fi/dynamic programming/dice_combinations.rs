use std::io;

fn solve(n: i64) -> i64 {
    const MOD: i64 = 1e9 as i64 + 7;

    let mut ways: Vec<i64> = vec![0; (n+1) as usize];
    ways[0] = 1;
    
    for w in 1..=n {
        let mut total: i64 = 0;
        for i in 1..=6 {
            if w-i >= 0 {
                total += ways[(w-i) as usize];
                if total >= MOD {
                    total -= MOD;
                }
            }
        }
        ways[w as usize] = total;
    }
    ways[n as usize]
}

fn main() {
    let n: i64 = {
        let mut line = String::new();
        let _ = io::stdin().read_line(&mut line);
        line.trim().parse::<i64>().unwrap()
    };

    println!("{ans}", ans = solve(n));
}