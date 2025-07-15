import viteReact from "@vitejs/plugin-react";
import { defineConfig, loadEnv } from "vite";

/** @type {import("vite").UserConfig} */
export default defineConfig(({ mode }) => {
  // @ts-expect-error: process.env not a global type
  const env = loadEnv(mode, process.cwd(), "");
  return {
    plugins: [
      viteReact({
        babel: {
          plugins: [
            ["babel-plugin-react-compiler"],
          ],
        },
      }),
    ],
    server: {
      proxy: {
        "/config.js": {
          target: env.VITE_SERVER_BASE_URL,
          changeOrigin: true,
        },
      },
    },
  };
});
