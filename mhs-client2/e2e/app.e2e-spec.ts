import { MhsClient2Page } from './app.po';

describe('mhs-client2 App', () => {
  let page: MhsClient2Page;

  beforeEach(() => {
    page = new MhsClient2Page();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
