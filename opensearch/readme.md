POC OpenSearch
===

## Goals
- [x] setup a opensearch cluster
- [] create an index
- [] configure an index
- [] index some documents
- [] search for documents
- [] reindex some documents


## dependencies
- make sure to have **docker** installed *(here I'm using the following version: `Docker version 24.0.7, build afdd53b`)*
- make sure to have **node** installed *(here I'm using the following version: `v20.10.0`)*
- make sure to configure the `vm.max_map_count`

## about the environment

#### containers:
- opensearch-node1: opensearchproject/opensearch:latest
- opensearch-node2: opensearchproject/opensearch:latest
- opensearch-dashboards: opensearchproject/opensearch-dashboards:latest


## Configuring the `vm.max_map_count`

To perform search and indexing operations, OpenSearch extensively utilizes the host's virtual memory. To ensure that the container has sufficient resources, apply the necessary changes to the host.

Set the available memory to 262144 (256K):

```bash
sudo sh -c 'echo "vm.max_map_count=262144" >> /etc/sysctl.conf'

```

Make sure to immediately apply the changes to the /etc/sysctl.conf file:

```bash
sudo sysctl -p

```

These adjustments are essential to ensure that OpenSearch has adequate resources for intensive search and indexing operations, optimizing system performance.



#### scripts
- playground (`pnpm run playground`)

## running the environment

```bash
# run the containers
docker compose up -d 

# then run the playground command
pnpm playground
```

## references
- [opensearch doc](https://opensearch.org/docs/1.0/opensearch/index/)
- [configuring opensearch container](https://opensearch.org/docs/1.0/opensearch/install/docker/)
- [configuring opensearch dashboard container](https://opensearch.org/docs/1.0/dashboards/install/docker/)