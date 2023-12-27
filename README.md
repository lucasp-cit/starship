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

TO-DO: We need to create a dev/start/serve script.

---

#4 - criacao do nosso code generator (https://nx.dev/core-features/plugin-features/use-code-generators)
