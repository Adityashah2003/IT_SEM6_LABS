import numpy as np

class KMeans:
    def __init__(self, n_clusters=2, max_iters=100, tol=1e-4):
        self.n_clusters = n_clusters
        self.max_iters = max_iters
        self.tol = tol
        self.centroids = None

    def fit(self, X):
        idx = np.random.choice(X.shape[0], self.n_clusters, replace=False)
        self.centroids = X[idx]

        for _ in range(self.max_iters):
            labels = self._assign_labels(X)

            new_centroids = self._calculate_centroids(X, labels)

            if np.all(np.abs(self.centroids - new_centroids) < self.tol):
                break

            self.centroids = new_centroids

    def _assign_labels(self, X):
        distances = np.sqrt(((X - self.centroids[:, np.newaxis])**2).sum(axis=2))
        return np.argmin(distances, axis=0)

    def _calculate_centroids(self, X, labels):
        centroids = np.zeros((self.n_clusters, X.shape[1]))
        for i in range(self.n_clusters):
            centroids[i] = np.mean(X[labels == i], axis=0)
        return centroids

    def predict(self, X):
        distances = np.sqrt(((X - self.centroids[:, np.newaxis])**2).sum(axis=2))
        return np.argmin(distances, axis=0)

# Example usage:
if __name__ == "__main__":
    with open("L5-L9/kmeans_data.txt", "r") as file:
        data = []
        for line in file:
            point = line.strip().split(',')
            data.append([float(point[0]), float(point[1])])
    X = np.array(data)

    kmeans = KMeans(n_clusters=3)
    kmeans.fit(X)

    labels = kmeans.predict(X)

    print("Cluster centroids:")
    print(kmeans.centroids)
    print("\nCluster labels:")
    print(labels)
