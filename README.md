#1 - criacao do workspace NX + CORE + SDK libs (https://nx.dev/getting-started/tutorials/integrated-repo-tutorial)

`npx create-nx-workspace@latest starship --preset=ts`

---

#2 - criacao da lib do CORE:

`npx nx generate @nx/js:library core --directory=libs/core --publishable --importPath=@starship/core`

Criei alguns scripts no package.json (build, deploy, lint e test).

TO-DO: precisamos criar algum tipo de serve / start script.

---

#3 - criacao da lib do SDK:

`npx nx generate @nx/js:library sdk --directory=libs/sdk --publishable --importPath=@starship/sdk`

Criei alguns scripts no package.json (build, deploy, lint e test).

TO-DO: precisamos criar algum tipo de serve / start script.

---

#4 - criacao do nosso code generator (https://nx.dev/core-features/plugin-features/use-code-generators)
