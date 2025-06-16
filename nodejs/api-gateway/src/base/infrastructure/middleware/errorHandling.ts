class ErrorHandling extends Error {
  constructor(message: string) {
    super(message);
  }
}

export class UnhandledError extends ErrorHandling {
  constructor(stack: string) {
    super("Unhandled Exception");
    this.name = "UnhandledError";
    this.stack = stack;
  }
}

export class LogicalError extends ErrorHandling {
  constructor(message: string) {
    super(message);
    this.name = "LogicalError";
  }
}
