var n=Object.defineProperty;var s=Object.getOwnPropertyDescriptor;var y=Object.getOwnPropertyNames;var i=Object.prototype.hasOwnProperty;var l=(a,t)=>{for(var e in t)n(a,e,{get:t[e],enumerable:!0})},d=(a,t,e,o)=>{if(t&&typeof t=="object"||typeof t=="function")for(let r of y(t))!i.call(a,r)&&r!==e&&n(a,r,{get:()=>t[r],enumerable:!(o=s(t,r))||o.enumerable});return a};var u=a=>d(n({},"__esModule",{value:!0}),a);var m={};l(m,{hello:()=>c});module.exports=u(m);var c=async(a,t)=>({statusCode:200,body:JSON.stringify({message:"My First Starship Lambda Function!!!!!!!",input:a},null,2)});0&&(module.exports={hello});
//# sourceMappingURL=handler.js.map
