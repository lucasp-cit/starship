import { Client, ClientOptions } from "@opensearch-project/opensearch";

interface ISearchService {
    setDocuments<T>(key: string, documents: T[]): Promise<unknown>;
}

export class OpensearchService  implements ISearchService {
    private client: Client
    
    constructor(connection: string, options: ClientOptions = {}) {
        this.client = new Client({
            ...options,
            node: connection
        })
    }

    setDocuments<T>(indexName: string, documents: T & { id: string } []) {
        return this.client.helpers.bulk({
            datasource: documents,
            onDocument: (doc) => {
                return {
                    index: { _index: indexName, _id: doc.id }
                }
            }
        })
    }

    search<T>(indexName: string, term: string) {
        return this.client.search({
            index: indexName,
            body: {
                query: {
                  match: {
                    title: {
                      query: term,
                    },
                  },
                },
              },
          });
    }
}