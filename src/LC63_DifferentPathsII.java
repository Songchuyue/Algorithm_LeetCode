public class LC63_DifferentPathsII {
	public static int uniquePathsWithObstacles_dfs(int[][] obstacleGrid) {
		int m = obstacleGrid.length, n = obstacleGrid[0].length;

		if(obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) return 0;
		if(m == 1 && n == 1) return 1;

		int[][] ans = new int[m][n];

		for(int i = 0; i < m; i++)
			for(int j = 0; j < n; j++)
				ans[i][j] = -1;

		ans[m - 1][n - 1] = 1;

		return dfs(obstacleGrid, ans, m, n, 0, 0);
	}

	private static int dfs(int[][] obstacleGrid, int[][] ans, int m, int n, int x, int y) {
		ans[x][y] = 0;
		if(x + 1 < m && obstacleGrid[x + 1][y] == 0) ans[x][y] += ans[x + 1][y] == -1 ? dfs(obstacleGrid, ans, m, n, x + 1, y) : ans[x + 1][y];
		if(y + 1 < n && obstacleGrid[x][y + 1] == 0) ans[x][y] += ans[x][y + 1] == -1 ? dfs(obstacleGrid, ans, m, n, x, y + 1) : ans[x][y + 1];
		return ans[x][y];
	}

	public static int uniquePathsWithObstacles_dp(int[][] obstacleGrid) {
		int m = obstacleGrid.length, n = obstacleGrid[0].length;

		if(obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) return 0;
		if(m == 1 && n == 1) return 1;

		int[][] ans = new int[m][n];
		ans[0][0] = 1;

		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(obstacleGrid[i][j] == 1) continue;

				ans[i][j] += (i > 0 ? ans[i - 1][j] : 0) + (j > 0 ? ans[i][j - 1] : 0);
			}
		}

		return ans[m - 1][n - 1];
	}

	public static void main(String[] args) {
		int[][] obstacleGrid = {
				{0,0,0,0},
				{0,1,1,0},
				{0,0,0,1},
				{0,0,0,0}
		};

		System.out.println(uniquePathsWithObstacles_dfs(obstacleGrid));
	}
}
