import numpy as np

def fit_kmeans(X, n_clusters=2, max_iters=100, tol=1e-4):
    centroids = initialize_centroids(X, n_clusters)
    for _ in range(max_iters):
        labels = assign_labels(X, centroids)
        new_centroids = calculate_centroids(X, labels, n_clusters)
        if np.all(np.abs(centroids - new_centroids) < tol):
            break
        centroids = new_centroids
    return centroids, labels

def initialize_centroids(X, n_clusters):
    idx = np.random.choice(X.shape[0], n_clusters, replace=False)
    return X[idx]

def assign_labels(X, centroids):
    distances = np.sqrt(((X - centroids[:, np.newaxis])**2).sum(axis=2))
    return np.argmin(distances, axis=0)

def calculate_centroids(X, labels, n_clusters):
    centroids = np.zeros((n_clusters, X.shape[1]))
    for i in range(n_clusters):
        centroids[i] = np.mean(X[labels == i], axis=0)
    return centroids

def predict_labels(X, centroids):
    distances = np.sqrt(((X - centroids[:, np.newaxis])**2).sum(axis=2))
    return np.argmin(distances, axis=0)

with open("L5-L9/kmeans_data.txt", "r") as file:
    data = []
    for line in file:
        point = line.strip().split(',')
        data.append([float(point[0]), float(point[1])])
X = np.array(data)

centroids, labels = fit_kmeans(X, n_clusters=3)

print("Cluster centroids:")
print(centroids)
print("\nCluster labels:")
print(labels)
