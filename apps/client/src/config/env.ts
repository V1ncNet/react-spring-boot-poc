function resolve(key: keyof NonNullable<Window["__ENV__"]>, fallback?: string): string {
  let value: string | undefined;

  value = import.meta.env[`VITE_${key}`];

  if (typeof window !== "undefined" && window.__ENV__) {
    value = window.__ENV__[key] ?? value;
  }

  value = value ?? fallback;

  if (!value) {
    throw new Error(`Missing mandatory configuration: '${key}'`);
  }

  return value;
}

export const env = {
  SERVER_BASE_URL: resolve("SERVER_BASE_URL"),
};
