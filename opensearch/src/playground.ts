import { OpensearchService } from "./opensearch.service";

const posts = require('./posts.json')

const host = "localhost";
const protocol = "http";
const port = 9200;
const auth = "admin:admin"; // For testing only. Don't store credentials in code.


(async () => {

type PostType = {
  userId: string;
  id: string;
  title: string;
  body: string;
}

const getConnectionString = () => `${protocol}://${auth}@${host}:${port}`

// conectar
const searchService = new OpensearchService(getConnectionString())

// const result = await searchService.setDocuments<PostType>("posts-index", posts)
// console.log(result)

const result2 = await searchService.search("posts-index", "qui est esse")
console.log(result2.body.hits.hits)

})()