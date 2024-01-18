#0 - SETUP
`npm install -g serverless`
`npm install --global nx@latest`

#1 - creating workspace NX + CORE + SDK libs (https://nx.dev/getting-started/tutorials/integrated-repo-tutorial)

`npx create-nx-workspace@latest starship --preset=ts`

`cd starship`

---

#2 - creating lib CORE:

`npx nx generate @nx/js:library core --directory=libs/core --publishable --importPath=@starship/core`

Exposed some scripts in the package.json (build, deploy, lint and test).

TO-DO: We need to create a dev/start/serve script.

---

#3 - creating lib SDK:

`npx nx generate @nx/js:library sdk --directory=libs/sdk --publishable --importPath=@starship/sdk`

Exposed some scripts in the package.json (build, deploy, lint and test).

TO-DO: We need to create a dev/start/serve script....

---

#4 - creating our first code generator (https://nx.dev/extending-nx/recipes/local-generators)

`npm install @nx/plugin --save-dev`

`nx g @nx/plugin:plugin starship-nx-plugin` -> used the name "starship-nx-plugin" for the created plugin.

`nx generate @nx/plugin:generator serverless-handler-generator --project=starship-nx-plugin` -> used the derived option.

TO-DO: check what is the typescript issue in this file:
starship/starship-nx-plugin/src/generators/serverless-handler-generator/jest-config.ts

---

#5 - creating a stack using the generator

`nx generate @starship/starship-nx-plugin:serverless-handler-generator <STACK_NAME>`

---

6 - Checkout all the Available commands generated in the project.json of the Stack (serve, deploy, etc)

Not sure why, but to run locally on MacOS it was necessary to run the following command:

`npm install -D serverless-esbuild esbuild`

Here is the command used:

`nx run <STACK_NAME>:serve --stage=<STAGE_NAME>`

you can check the lambda returning data by copy/pasting the URL generated on your browser.
