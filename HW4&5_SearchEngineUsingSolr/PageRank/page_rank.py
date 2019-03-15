import networkx as nx
import math

G = nx.read_edgelist("edgeList.txt", create_using=nx.DiGraph())

pagerank = nx.pagerank(G, alpha=0.85, personalization=None, max_iter=30, tol=1e-06, nstart=None, weight='weight', dangling=None)

with open("external_pageRankFile.txt", "w", encoding="utf-8") as f:
    for pageid in pagerank:
        f.write("/usr/local/solr/Pages/" + pageid + "=" + str(math.log(pagerank[pageid])) + "\n")


